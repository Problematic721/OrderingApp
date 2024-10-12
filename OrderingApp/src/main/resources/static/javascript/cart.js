let cart = [];
let cartCount = 0;

function addToCart(button) {
	const dishId = button.getAttribute('data-dish-id');
	const dishName = button.getAttribute('data-dish-name');
	const dishPrice = parseFloat(button.getAttribute('data-dish-price'));
	const existingDish = cart.find(item => item.id === dishId);

	if (existingDish) {
		existingDish.quantity++;
	} else {
		cart.push({ id: dishId, name: dishName, price: dishPrice, quantity: 1 });
	}
	cartCount++;
	updateCartBadge();
}

function updateCartBadge() {
	document.getElementById('cartBadge').innerText = cartCount;
}

function showCart() {
	renderCart();
	const cartModal = new bootstrap.Modal(document.getElementById('cartModal'));
	cartModal.show();
}

function renderCart() {
	const cartContent = document.getElementById('cart-content');
	cartContent.innerHTML = '';

	if (cart.length === 0) {
		cartContent.innerHTML = '<p>Your cart is empty.</p>';
	} else {
		cart.forEach(item => {
			const cartItem = document.createElement('div');
			cartItem.innerHTML = `
			<div>${item.name} - $${item.price.toFixed(2)} (Quantity: ${item.quantity}) 
			                    <button class="btn btn-danger btn-sm float-end" 
			                            data-id="${item.id}" 
			                            onclick="removeFromCart(this)">Remove</button>
			                </div>
            `;
			cartContent.appendChild(cartItem);
		});
	}
}

function removeFromCart(button) {
    const dishId = button.getAttribute('data-id');
    const index = cart.findIndex(item => item.id === dishId);

    if (index !== -1) {
        cartCount -= cart[index].quantity;
        cart.splice(index, 1);
        updateCartBadge();
        renderCart();
    }
}