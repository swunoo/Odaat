// Clicking the Add button.
ADD_BTN.addEventListener('click', e => {
    MODAL.style.display = 'flex';
});

// Changing modal inputs based on type.
[DAYS, FROM, TO].forEach(item => item.classList.add('hidden')); // Initial
TYPE.addEventListener('change', e => {
    TYPE.children[1].classList.toggle('bg-blue-forced');
    [DAYS, FROM, TO, DEADLINE, ESTIMATED].forEach(item => item.classList.toggle('hidden'));
});

// Changing disabled attribute for END.
STATUS.addEventListener('change', e => {
    const inputBox = END.children[1];
    inputBox.disabled = !inputBox.disabled;
})

// Closing the Modal with X.
CLOSE.addEventListener('click', e => {
    MODAL.style.display = 'none';
});

