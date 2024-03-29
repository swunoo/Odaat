// Sample card.
const cardNode = document.getElementsByClassName(THEME_CLASS)[0];

// Clicking the Add button.
ADD_BTN.addEventListener('click', e => openModal(MODAL));

// Deleting a Theme.
function deleteTheme(item){
    const id = item.getAttribute('data-delete-id');
    fetch(API_ROUTE + "/" + id, {
        method: 'DELETE',
        headers: {
            "X-CSRF-TOKEN": CSRF_TOKEN
        },
        credentials: 'include'
    })
        .then(res => {
            if(res.status !== 200) throw new Error(res.text());
            return res.text();
        })
        .then(response => {
            oldCard = document.getElementById(id);
            oldCard.remove();
            closeModal(MODAL);
        })
        .catch(err => {
            console.error(err);
        });
}

// Changing modal inputs based on type.
TYPE.addEventListener('change', adjustModal);

// When theme menu icon is clicked
function openThemeMenu(item){
    let container = item;
    // Find the theme-container element.
    while (!container.classList.contains(THEME_CLASS)){
        container = container.parentElement;
    }

    // TODO: Show modal for update/delete using data from [container] element.
    const themeData = {};
    // Building theme object from themeUICard element.
    const traverseAndExtract = tag => {

        // progress tag is hidden if routine.
        if(tag.nodeName === 'PROGRESS'){
            themeData.type = tag.className.includes('hidden') ? 'ROUTINE' : 'PROJECT';
        }

        const data = tag.getAttribute(DATA_ATTRIBUTE);
        if(data !== null){

            // For 'completedAt'.
            if(data === 'calc-completedAt'){

                if(tag.textContent === 'Present'){
                    themeData['completedAt'] = null;
                } else {
                    const completionTime = Date.parse(tag.textContent);
                    const currentTime = Date.now();
                    if(completionTime > currentTime){
                        themeData['deadline'] = tag.textContent;
                    } else {
                        themeData['completedAt'] = tag.textContent;
                    }
                }
                
            } else {
                const [ attr, key ] = data.split('-'); // [e.g.] data-theme-key = "value-startTime"
                if(attr !== 'calc') themeData[key] = tag[attr];
            }
        }

        if(tag.children.length > 0){
            [...tag.children].forEach(traverseAndExtract);
        }
    }

    traverseAndExtract(container);

    // Builds theme modal
    IMG.src = themeData.imgName;
    setValue(NAME, themeData.name);
    setValue(TYPE, themeData.type);
    setValue(PROGRAM, themeData.program);
    setValue(DESCRIPTION, themeData.description);
    setValue(START, themeData.startedAt);
    setValue(END, themeData.completedAt);
    setValue(DEADLINE, themeData.deadline);
    setValue(DAYS, themeData.repeatedOn);
    setValue(STATUS, themeData.completedAt === null ?  'IN PROGRESS' : 'ENDED');
    setValue(SPENT, themeData.timeSpent);
    setValue(ESTIMATED, themeData.timeEstimated);
    setValue(FROM, themeData.startTime);
    setValue(TO, themeData.endTime);

    DELETEBTN.setAttribute('data-delete-id', themeData.id);

    DELETEBTN.classList.remove('hidden');
    SUBMIT.textContent = 'Update'
    openModal(MODAL, false);
}

// Changing disabled attribute for END based on whether the theme has been completed.
STATUS.addEventListener('change', e => {
    const inputBox = END.children[1];
    inputBox.disabled = !inputBox.disabled;
})

// Closing the Modal with X.
CLOSE.addEventListener('click', e => closeModal(MODAL));

// Submitting the form
FORM.addEventListener('submit', async (e) => {
    // Stops page reloading.
    e.preventDefault();

    const apiMethod = SUBMIT.textContent.toLowerCase() === 'update' ? 'PUT' : 'POST';

    // Collects user input.
    // TODO: validation
    const parentTheme = createNewTheme(
        getValue(NAME), getValue(PROGRAM), getValue(DESCRIPTION), getValue(END)
    );

    const theme = (getValue(TYPE)==='PROJECT') 
                    ? createProject(parentTheme, getValue(ESTIMATED), getValue(DEADLINE))
                    : createRoutine(parentTheme, getValue(DAYS), true, getValue(FROM), getValue(TO));

    // Closes modal and Shows NEW_THEME as LOADING.
    closeModal(MODAL);
    const themeCardUI = buildThemeCard(theme);
    if(apiMethod === 'PUT'){
        const themeID = DELETEBTN.getAttribute('data-delete-id');
        theme.id = themeID;
        oldCard = document.getElementById(themeID);
        oldCard.remove();
    }
    THEME_WRAPPER.insertBefore(
        themeCardUI, THEME_WRAPPER.firstChild
    )

    // Sends data and Waits for res.
    await fetch(API_ROUTE + "/" + theme.type.toLowerCase(), {
        method: apiMethod,
        headers: {
            "Content-Type": "application/json",
            "X-CSRF-TOKEN": CSRF_TOKEN
        },
        credentials: 'include',
        body: JSON.stringify(theme)
    })
        // If successful, Shows NEW_THEME as SUCCESSFUL, else Shows as FAILED.
        .then(res => {
            if(res.status !== 201 && res.status !== 200) throw new Error(res.text());
            return res.text();
        })
        .then(response => {
            if(apiMethod === 'POST'){
                theme.id = response.split(":")[1].trim();
                themeCardUI.id = theme.id;
            }
            themeCardUI.querySelector('.loading-curtain').classList.add('hidden-forced');
        })
        .catch(err => {
            themeCardUI.querySelector('.loading-curtain > h3').textContent = "Failed. Please reload."
            throw new Error(err)
        });
});

// Builds the UI of the theme data using Thymeleaf fragment and theme argument.
function buildThemeCard(originalTheme){

    const theme = JSON.parse(JSON.stringify(originalTheme));

    // Adds dirpath to imagename if not already included.
    if(theme.imgName !== null && !theme.imgName.includes(IMG_DIR)){
        theme.imgName = IMG_DIR + theme.imgName;
    }

    // Breaks down the structure of a theme-card-ui
    const themeCardUI = document.createElement('div');
    themeCardUI.className = THEME_CLASS;
    themeCardUI.setAttribute('data-theme-key', 'id-id');
    themeCardUI.innerHTML = cardNode.innerHTML;

    // Create a new theme-card using theme argument
    const traverseTags = tag => {
        const key = tag.getAttribute(DATA_ATTRIBUTE)

        if(key === 'calc-progress' && theme.type === "PROJECT"){
            tag.value = theme.timeEstimated > 0 ? (theme.timeSpent/theme.timeEstimated) * 100 : 0;
        } else if(key === 'calc-completedAt'){
            tag.textContent = theme.completedAt !== null ? theme.completedAt : (
                theme.type === 'ROUTINE' ? 'Present' : theme.deadline
            ) 
        } else if(key === 'id-id') {
            tag.id = theme.id;
        } else if(key !== null) {
            const [attr, val] = key.split('-'); // "textContent-[key]" -> "textContent", "[key]"
            if(theme[val] !== null) tag[attr] = theme[val];
        }

        if(tag.children.length > 0){
            [...tag.children].forEach(traverseTags);
        }
    }

    [...themeCardUI.children].forEach(traverseTags);

    if(theme.type === 'PROJECT'){
        themeCardUI.getElementsByClassName('theme-header')[0].classList.add('bg-white');
        const classOfProgress = themeCardUI.querySelector('progress').classList;
        const classOfRoutinePlan = themeCardUI.querySelector('.routine-plan').classList;
        if(classOfProgress.contains('hidden')) classOfProgress.remove('hidden');
        if(!classOfRoutinePlan.contains('hidden')) classOfRoutinePlan.add('hidden');
    } else {
        themeCardUI.getElementsByClassName('theme-header')[0].classList.add('bg-gray-dark');
        const classOfProgress = themeCardUI.querySelector('progress').classList;
        const classOfRoutinePlan = themeCardUI.querySelector('.routine-plan').classList;
        if(!classOfProgress.contains('hidden')) classOfProgress.add('hidden');
        if(classOfRoutinePlan.contains('hidden')) classOfRoutinePlan.remove('hidden');
    }

    const loadingCurtain = document.createElement('div');
    loadingCurtain.classList.add('loading-curtain');
    loadingCurtain.innerHTML = "<h3>Loading</h3>"
    themeCardUI.append(loadingCurtain);

    // Returns the ui
    return themeCardUI;
}

// MOCK
const mockProject = createProject(
    createNewTheme("Mock", "JOB", "Lorem Ipsum Dolor Sit Amet"),
    10, "2023-11-19"
);
const mockRoutine = createRoutine(
    createNewTheme("Routine", "CHORES", "Routine description"),
    ["SUN", "MON"], true, "10:30", "17:30" 
)