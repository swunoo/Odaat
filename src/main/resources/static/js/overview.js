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
FORM.addEventListener('submit', e => {
    // Stops page reloading.
    e.preventDefault();

    // Collects user input.
    // TODO: validation
    const parentTheme = createNewTheme(
        NAME.value, PROGRAM.value, DESCRIPTION.value
    );
    const theme = (TYPE.value==='PROJECT') 
                    ? createProject(parentTheme, ESTIMATED.value, DEADLINE.value)
                    : createRoutine(parentTheme, DAYS.value, true, FROM.value, TO.value);

    // Closes modal and Shows NEW_THEME as LOADING.
    closeModal(MODAL);
    THEME_WRAPPER.insertBefore(
        buildThemeCard(theme), THEME_WRAPPER.firstChild
    )

    // Builds the request object.

    // Sends data and Waits for res.

    // If successful, Shows NEW_THEME as SUCCESSFUL, else Shows as FAILED.
});

// Builds the UI of the theme data using Thymeleaf fragment and theme argument.
function buildThemeCard(theme){
    // Breaks down the structure of a theme-card-ui
    const cardNode = document.getElementsByClassName(THEME_CLASS)[0];
    const themeCardUI = document.createElement('div');
    themeCardUI.className = THEME_CLASS;
    themeCardUI.innerHTML = cardNode.innerHTML;

    // Create a new theme-card using theme argument
    const traverseTags = tag => {
        console.log(tag);
        const key = tag.getAttribute(DATA_ATTRIBUTE)
        log(key);
        if(tag.children.length > 0){
            [...tag.children].forEach(traverseTags);
        }
    }
    [...themeCardUI.children].forEach(traverseTags);

    //CONTINUE BUILDING THE UI
    //MAKE IT WORK FOR PROJECT/ROUTINE.


    // Returns the ui
}

function log(msg){
    console.log(msg);
    // console.log(JSON.stringify(msg));
}

// MOCK
const mockTheme = createProject(
    createNewTheme("Mock", "JOB", "Lorem Ipsum Dolor Sit Amet"),
    10, "2023-11-19"
);

console.log("Mock");
buildThemeCard(mockTheme);
