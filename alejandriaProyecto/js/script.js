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
    window.location.href="index.html"; //redirige al inicio
}

//funcion para validar el registro
function validarRegistro(event){
    event.preventDefault();
    alert("¡Cuenta creada exitosamente! Ahora puedes iniciar sesión.");
    window.location.href="login.html";
}