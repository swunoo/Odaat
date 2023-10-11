function openModal(modalEle){
    modalEle.style.display = 'flex';
    document.body.style.overflow = 'hidden';
}

function closeModal(modalEle){
    modalEle.style.display = 'none';
    document.body.style.overflow = 'auto';
}

// Gets value of input, select and textarea tags inside the {parent}.
function getValue(parent){
    // For DayType (multiple) selector
    if(parent === DAYS){

        const options = [...parent.querySelector('select').options];
        return options.map(option => {
            return (option.selected ? option.value : null);
        }).filter(e => e !== null);

        // Padding the array to work well with Mapper.
        while(selection.length < options.length) selection.push("");

        return selection;
    }

    let userInput;
    if((userInput = parent.querySelector('input')) !== null) return userInput.value;
    else if((userInput = parent.querySelector('select')) !== null) return userInput.value;
    else if((userInput = parent.querySelector('textarea')) !== null) return userInput.value;
    else return null;
}