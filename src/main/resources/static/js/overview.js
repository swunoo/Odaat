// Clicking the Add button.
ADD_BTN.addEventListener('click', e => openModal(MODAL));

// Changing modal inputs based on type.
[DAYS, FROM, TO].forEach(item => item.classList.add('hidden-forced')); // Initial
TYPE.addEventListener('change', e => {
    TYPE.children[1].classList.toggle('bg-blue-forced');
    [DAYS, FROM, TO, DEADLINE, ESTIMATED].forEach(item => item.classList.toggle('hidden-forced'));
});

// Changing disabled attribute for END.
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

    // Collects user input.
    // TODO: validation
    const parentTheme = createNewTheme(
        getValue(NAME), getValue(PROGRAM), getValue(DESCRIPTION)
    );

    log(parentTheme);
    log("name, program, description, estimated, deadline, days, from, to")

    const theme = (getValue(TYPE)==='PROJECT') 
                    ? createProject(parentTheme, getValue(ESTIMATED), getValue(DEADLINE))
                    : createRoutine(parentTheme, getValue(DAYS), true, getValue(FROM), getValue(TO));

    // Closes modal and Shows NEW_THEME as LOADING.
    closeModal(MODAL);
    THEME_WRAPPER.insertBefore(
        buildThemeCard(theme), THEME_WRAPPER.firstChild
    )

    // Sends data and Waits for res.
    log('imgDir:')
    log(IMG_DIR)
    log('apiRoute:')
    log(API_ROUTE)
    log("theme to be sent:")
    log(theme);

    await fetch(API_ROUTE, {
        method: 'POST',
        mode: 'no-cors',
        credentials: 'include',
        body: JSON.stringify(theme)
    }).then(res => res.json())
        .then(res => log(res))
        .catch(err => log(err));


    // If successful, Shows NEW_THEME as SUCCESSFUL, else Shows as FAILED.
});

// Builds the UI of the theme data using Thymeleaf fragment and theme argument.
function buildThemeCard(theme){

    // Adds dirpath to imagename if not already included.
    if(theme.imgName !== null && !theme.imgName.includes(IMG_DIR)){
        theme.imgName = IMG_DIR + theme.imgName;
    }

    // Breaks down the structure of a theme-card-ui
    const cardNode = document.getElementsByClassName(THEME_CLASS)[0];
    const themeCardUI = document.createElement('div');
    themeCardUI.className = THEME_CLASS;
    themeCardUI.innerHTML = cardNode.innerHTML;

    // Create a new theme-card using theme argument
    const traverseTags = tag => {
        const key = tag.getAttribute(DATA_ATTRIBUTE)

        if(key === 'calc-progress' && theme.type === "PROJECT"){
            tag.value = (theme.timeSpent/theme.timeEstimated) * 100;
        } else if(key === 'calc-completedAt'){
            tag.textContent = theme.completedAt !== null ? theme.completedAt : (
                theme.type === 'ROUTINE' ? 'Present' : theme.deadline
            ) 
        } else if(key === 'id-card') {
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
    loadingCurtain.innerHTML = "<h3>Adding</h3>"
    themeCardUI.append(loadingCurtain);

    // Returns the ui
    return themeCardUI;
}

function log(msg){
    console.log(msg);
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

// Testing theme card
// log("Mock");
// const proj = buildThemeCard(mockProject);
// const rout = buildThemeCard(mockRoutine);
// THEME_WRAPPER.appendChild(proj);
// THEME_WRAPPER.appendChild(rout);

// setTimeout(() => {
//     log('adding');
//     proj.querySelector('.loading-curtain').classList.add('hidden-forced');
// }, 2000);

// setTimeout(() => {
//     log('adding');
//     rout.querySelector('.loading-curtain').classList.add('hidden-forced');
// }, 1000);
