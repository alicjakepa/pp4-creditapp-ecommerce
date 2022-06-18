const getCurrentOffer = () => {
    return fetch('/api/sales/offer')
      .then(response => response.json());
}

const refreshOffer = (offer, offerEl) => {
    offerEl.querySelector('.cart__total').textContent = offer.total;
    offerEl.querySelector('.cart__itemsCount').textContent = offer.itemsCount;
}

const getProducts = () => {
    return fetch('/api/products')
      .then(response => response.json());
}
const createHtmlElementFromString = (template) => {
    let el = document.createElement('div');
    el.innerHTML = template.trim();
    return el.firstChild;
}

const createHtmlEl = (product) => {
    const template = `
        <li>
            <h5>${product.name}</h5>
            <span>${product.price}</span>
            <button data-productId="${product.id}">
                add to cart
            </button>
        </li>
    `;

    return createHtmlElementFromString(template);
}

const handleAddToCart = (event) => {
    const productId = event.target.getAttribute('data-productId');

    console.log(`i am adding ${productId}`);

    fetch(`/api/sales/add-product/${productId}`, {
        method: 'POST'
    })
        .then(r => getCurrentOffer())
        .then(offer => refreshOffer(offer, document.querySelector('.cart')))
    ;
}

(() => {
    getCurrentOffer()
        .then(offer => refreshOffer(offer, document.querySelector('.cart')))
    alert('it works');

    const productsList = document.querySelector('.productsList');
        getProducts()
            .then(products => {
                products
                    .map(product => createHtmlEl(product))
                    .forEach(productEl => {
                        productEl.addEventListener('click', handleAddToCart);
                        productsList.appendChild(productEl)
                    });
            });
})();