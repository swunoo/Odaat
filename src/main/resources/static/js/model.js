function createTheme(
    name, program, timeSpent, description,
    imgName, startedAt, completedAt
){
    
    this.id = new Date().getTime();
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