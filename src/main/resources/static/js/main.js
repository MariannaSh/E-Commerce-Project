getProducts = () => {
    return fetch("/api/products")
        .then(response => response.json());
    }

getCurrentOffer = () => {
    return fetch("/api/current-offer")
        .then(response => response.json());
}

const createProductHtml = (productData) => {
    const template = `
        <div class="product">
            <h4>${productData.name}</h4>
            <img src = "https://cdn.britannica.com/70/234870-050-D4D024BB/Orange-colored-cat-yawns-displaying-teeth.jpg"/>
            <div class="product_price">
                <span>${productData.price}</span>
                <button data-id="${productData.id}">Add to cart +</button>
            </div>
        </div>
    `
    const productEl = document.createElement("li");
    productEl.innerHTML = template.trim();

    return productEl;
}

document.addEventListener("DOMContentLoaded", () => {
    const productsListEl = document.querySelector("#productsList");
    getProducts()
        .then(productsAsJsonObj => productsAsJsonObj.map(createProductHtml))
        .then(productsAsHtmlEl => {
            productsAsHtmlEl.forEach(productEl => productsListEl.appendChild(productEl))
        })
});