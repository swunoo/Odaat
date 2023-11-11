function createTheme(
    name, program, timeSpent, description,
    imgName, startedAt, completedAt
){
    // TMP
    // TODO: use server-generated id.
    
    this.id = new Date().getTime();
    // TODO: add imgAPIs
    return {
        name, program, timeSpent, description, imgName, startedAt, completedAt
    }
}

function createNewTheme(
    name, program, description, completedAt
){
    return createTheme(
        name, program, 0, description, 't_1_java.png', new Date().toISOString().slice(0, 10), completedAt
    );
}

function createProject(
    theme, timeEstimated, deadline
){
    this.type = "PROJECT";
    return {
        ...theme, type, timeEstimated, deadline
    };
}

function createRoutine(
    theme, repeatedOn, isActive, startTime, endTime
){
    this.type = "ROUTINE";
    return {
        ...theme, type, repeatedOn, isActive, startTime, endTime
    };
}

function createTask(
    themeId, date, description, status, startTime, endTime
){
    return { themeId, date, description, status, startTime, endTime };
}