let precio = 0;

function seleccionarLibro(nombre, valor) {
    precio = valor;
    document.getElementById("libro").innerText = nombre;
    document.getElementById("precio").innerText = valor;
    document.getElementById("total").innerText = valor;
    document.getElementById("cantidad").value = 1;
}

function calcularTotal() {
    let cantidad = document.getElementById("cantidad").value;
    let total = precio * cantidad;
    document.getElementById("total").innerText = total;
}

function comprar() {
    alert("Compra realizada correctamente.");
}

//funcion de mensaje enviado en contactos
function enviarFormulario() {
    alert("Mensaje enviado correctamente.");
    document.querySelector("form").reset();
}


//funcion para validar el login
function validarLogin(event){
    event.preventDefault(); //evita que se recargue la pagina
    alert("¡Inicio de sesion exitoso!");
    window.location.href="/"; //redirige al inicio
}

//funcion para validar el registro
function validarRegistro(event){
    event.preventDefault();
    alert("¡Cuenta creada exitosamente! Ahora puedes iniciar sesión.");
    window.location.href="/login";
}

//funciones de la pagina de detalle del libro
function maximoDetalle() {
    const max = parseInt(document.getElementById("cantidadDetalle").max);
    return isNaN(max) ? 99 : max;   // el maximo es el stock del libro
}

function cambiarCantidad(delta) {
    const input = document.getElementById("cantidadDetalle");
    let cantidad = parseInt(input.value) || 1;
    cantidad = Math.max(1, Math.min(maximoDetalle(), cantidad + delta));
    input.value = cantidad;
    actualizarTotalDetalle();
}

function actualizarTotalDetalle() {
    const input = document.getElementById("cantidadDetalle");
    let cantidad = parseInt(input.value);
    if (isNaN(cantidad) || cantidad < 1) cantidad = 1;
    if (cantidad > maximoDetalle()) cantidad = maximoDetalle();
    input.value = cantidad;
    const precioUnitario = Number(document.getElementById("totalDetalle").dataset.precio);
    document.getElementById("totalDetalle").innerText = (precioUnitario * cantidad).toFixed(2);
}

function agregarAlCarrito(titulo) {
    const cantidad = document.getElementById("cantidadDetalle").value;
    alert("Se agregó al carrito: " + cantidad + " x " + titulo);
}