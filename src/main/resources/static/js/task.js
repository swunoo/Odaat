// New task creation
TASK_CREATE.addEventListener('submit', e => {
    e.preventDefault();
    const task = createTask(
        TASK_CREATE_THEME.value, 
        new Date().toISOString().slice(0, 10),
        TASK_CREATE_DESCRIPTION.value,
        'TODO',
        new Date().toTimeString().slice(0, 5),
        new Date(Date.now() + 3600 * 1000).toTimeString().slice(0, 5)
    )
    console.log("task:");
    console.log(task);

    const createTaskInputs = [TASK_CREATE_THEME, TASK_CREATE_DESCRIPTION, TASK_CREATE_BUTTON];
    createTaskInputs.forEach(ele => ele.disabled = true);

    // Send fetch request, turn disable to false after success.
    fetch(API_ROUTE + "/" + theme.type.toLowerCase(), {
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
            console.log(response);
            console.log("themecardUI");
            console.log(themeCardUI);
            if(apiMethod === 'POST'){
                theme.id = response.split(":")[1].trim();
                themeCardUI.id = theme.id;
            }
            themeCardUI.querySelector('.loading-curtain').classList.add('hidden-forced');
        })
        .catch(err => {
            console.log("ERROR THROWN.");
            themeCardUI.querySelector('.loading-curtain > h3').textContent = "Failed. Please reload."
            throw new Error(err)
        });
})