function createTheme(
    name, program, timeSpent, description,
    imgName, startedAt, completedAt
){
    // TODO: add imgAPIs
    return {
        name, program, timeSpent, description, imgName, startedAt, completedAt
    }
}

function createNewTheme(
    name, program, description
){
    return createTheme(
        name, program, 0, description, 't_1_java.png', new Date(), null
    );
}

function createProject(
    theme, timeEstimated, deadline
){
    return {
        ...theme, timeEstimated, deadline
    };
}

function createRoutine(
    theme, repeatedOn, isActive, startTime, endTime
){
    return {
        ...theme, repeatedOn, isActive, startTime, endTime
    };
}