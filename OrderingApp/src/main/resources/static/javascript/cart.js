function showCart() {
	const cartModal = new bootstrap.Modal(document.getElementById('cartModal'));
	cartModal.show();
}

// Cart Functions
let cartItems = [];

function addToCart(item) {
	const existingItem = cartItems.find(cartItem => cartItem.id === item.id);
	if (existingItem) {
		existingItem.quantity += 1; // Increment quantity if item exists
	} else {
		cartItems.push({ ...item, quantity: 1 }); // Add new item
	}
	updateCart();
}

// Function to update the cart modal display
function updateCart() {
	const cartItemsContainer = document.getElementById('cartItems');
	cartItemsContainer.innerHTML = ''; 
	let totalPrice = 0;

	// Create cart item elements
	cartItems.forEach(item => {
		const itemPrice = item.price * item.quantity;
		totalPrice += itemPrice;

		const itemElement = document.createElement('div');
		itemElement.className = 'list-group-item d-flex justify-content-between align-items-center';
		itemElement.innerHTML = `
            <span class="item-name">${item.name}</span>
            <span class="item-quantity">Qty: ${item.quantity}</span>
            <span class="item-price">$${itemPrice.toFixed(2)}</span>
            <button class="btn btn-danger btn-sm remove-item" data-item-id="${item.id}">Remove</button>
        `;
		cartItemsContainer.appendChild(itemElement);
	});

	updateCartBadge()
	document.getElementById('totalPrice').innerText = `$${totalPrice.toFixed(2)}`;
}

// Function to remove item from cart
function removeFromCart(itemId) {
	cartItems = cartItems.filter(item => item.id !== itemId); // Filter out the item
	updateCart();
}

// Event delegation to handle remove item clicks
document.getElementById('cartItems').addEventListener('click', function(event) {
	if (event.target.classList.contains('remove-item')) {
		const itemId = parseInt(event.target.getAttribute('data-item-id'), 10);
		removeFromCart(itemId);
	}
});

document.querySelectorAll('.add-to-cart').forEach(button => {
	button.addEventListener('click', function() {
		const dishId = parseInt(this.getAttribute('data-dish-id'), 10);
		const dishName = this.getAttribute('data-dish-name');
		const dishPrice = parseFloat(this.getAttribute('data-dish-price'));

		const item = {
			id: dishId,
			name: dishName,
			price: dishPrice
		};
		
		addToCart(item);
	});
});

function updateCartBadge() {
	const cartBadge = document.getElementById('cartBadge');


	cartBadge.textContent = cartItems.length;

	if (cartItems.length === 0) {
		cartBadge.style.display = 'none';
	} else {
		cartBadge.style.display = 'block';
	}
}

// Order Functions
function placeOrder() {
	
	const tableCode = getQueryParameter('tableCode');
	
	if (cartItems.length === 0) {
		alert("Your cart is empty. Please add items to the cart before placing an order.");
		return;
	}

	const orderItems = cartItems.map(dish => ({
	        dishId: dish.id,
	        quantity: dish.quantity
	    }));
	
	const orderDTO = {
	        tableCode: tableCode,
	        orderItems: orderItems
	    };

		fetch('/orders/place', { 
		    method: 'POST',
		    headers: {
		        'Content-Type': 'application/json',
		    },
		    body: JSON.stringify(orderDTO),
		})
		.then(response => {
		    if (!response.ok) {
		        throw new Error(`HTTP error! Status: ${response.status}`);
		    }
		    return response.json(); 
		})
		.then(data => {
		    alert("Order placed successfully!");
		    cartItems = [];
		    updateCartBadge();
		    const cartModal = bootstrap.Modal.getInstance(document.getElementById('cartModal'));
		    cartModal.hide();
		})
		.catch(error => {
		    console.error('There was a problem with the fetch operation:', error);
		    alert("There was an error placing your order. Please try again.");
		});
}

// Paramter Functions
function getQueryParameter(name) {
    const urlParams = new URLSearchParams(window.location.search);
    return urlParams.get(name);
}
