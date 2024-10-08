function showEditForm(id) {
	document.getElementById('text-' + id).style.display = 'none';
	document.getElementById('editForm-' + id).style.display = 'inline-block';
	document.getElementById('editBtn-' + id).style.display = 'none';
	document.getElementById('removeBtn-' + id).style.display = 'none';
}
function showAddForm() {
	document.getElementById('addButtonContainer').classList
		.add('d-none');
	document.getElementById('addFormContainer').classList
		.remove('d-none');
}