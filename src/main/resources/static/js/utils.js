function adjustModal(){
    if(getValue(TYPE) === 'PROJECT'){
        console.log('PROJECT');
        TYPE.children[1].classList.remove('bg-blue-forced');
        [DAYS, FROM, TO].forEach(item => item.classList.add('hidden-forced'));
        [DEADLINE, ESTIMATED].forEach(item => item.classList.remove('hidden-forced'));
    } else if (getValue(TYPE) === 'ROUTINE'){
        console.log('ROUTINE');
        TYPE.children[1].classList.add('bg-blue-forced');
        [DAYS, FROM, TO].forEach(item => item.classList.remove('hidden-forced'));
        [DEADLINE, ESTIMATED].forEach(item => item.classList.add('hidden-forced'));
    }
}

function openModal(modalEle, addDefault = true){
    
    if(addDefault){
        // Populating with default values
        setValue(TYPE, 'PROJECT');
        setValue(STATUS, 'IN PROG.');
        DELETEBTN.classList.add('hidden');
        SUBMIT.textContent = 'Create';
    }
    
    adjustModal();
    modalEle.style.display = 'flex';
}

function closeModal(modalEle){

    const clearData = ele => {
        if(['INPUT', 'SELECT', 'TEXTAREA'].includes(ele.nodeName))   ele.value = '';
        if(ele.children.length > 0){
            [...ele.children].forEach(clearData);
        }
    }

    clearData(modalEle);

    modalEle.style.display = 'none';
}

// Gets value of input, select and textarea tags inside the {parent}.
function getValue(parent){
    // For DayType (multiple) selector
    if(parent === DAYS){
        const options = [...parent.querySelector('select').options];
        return options.map(option => {
            return (option.selected ? option.value : null);
        }).filter(e => e !== null);
    }

    let userInput;
    for(let tag of ['input', 'select', 'textarea']){
        if((userInput = parent.querySelector(tag)) !== null){
            return userInput.value; 
        }
    }
}

// Sets value of input, select and textarea tags inside the {parent}.
function setValue(parent, value){
    // For DayType (multiple) selector
    if(parent === DAYS){
        [...parent.querySelector('select').options].forEach(option => {
            if(value.includes(option)) option.selected = true;
        })
    } else {
        let userInput;
    
        for(let tag of ['input', 'select', 'textarea']){
            if((userInput = parent.querySelector(tag)) !== null){
                userInput.value = value;
                return;
            }
        }
    }

}