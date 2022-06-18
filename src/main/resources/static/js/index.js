const getCurrentOffer = () => {
    return fetch('/api/sales/offer')
      .then(response => response.json());
}

const refreshOffer = (offer, offerEl) => {
    offerEl.querySelector('.cart__total').textContent = offer.total;
    offerEl.querySelector('.cart__itemsCount').textContent = offer.itemsCount;
}

(() => {
    getCurrentOffer()
        .then(offer => refreshOffer(offer, document.querySelector('.cart')))
    alert('it works');
})();