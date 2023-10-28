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
    
    fetch(API_ROUTE, {
        method: 'POST',
        headers: {
            "Content-Type": "application/json",
            "X-CSRF-TOKEN": CSRF_TOKEN
        },
        credentials: 'include',
        body: JSON.stringify(task)
    })
        .then(res => {
            if(res.status !== 201 && res.status !== 200) throw new Error(res.text());
            return res.text();
        })
        .then(response => {
            console.log("response");
            console.log(response);
        });
})