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

function showQRCode(id, tableCode) {
	var qrImageElement = document.getElementById('qrImage-' + id);
	var qrImageUrl = '/generateQr/' + tableCode;

	qrImageElement.src = qrImageUrl;
}

function downloadQRCode(id) {
	var qrImageUrl = document.getElementById('qrImage-' + id).src;

	var link = document.createElement('a');
	link.href = qrImageUrl;
	link.download = 'QRCode-' + id + '.png';
	link.click();
}