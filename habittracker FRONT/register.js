const modal = document.getElementById("modalRegistro");
const abrir = document.getElementById("botonRegistro");
const cerrar = document.getElementById("cerrarModal");

// abrir modal
abrir.addEventListener("click", (e) => {
    e.preventDefault();
    modal.style.display = "flex";
});

// cerrar modal
cerrar.addEventListener("click", () => {
    modal.style.display = "none";
});

// cerrar haciendo click afuera
window.addEventListener("click", (e) => {
    if (e.target === modal) modal.style.display = "none";
});

// ENVIAR REGISTRO
document.getElementById("formRegistro").addEventListener("submit", async (e) => {
    e.preventDefault();

    const email = document.getElementById("emailRegistro").value;
    const pass = document.getElementById("passRegistro").value;

    const resp = await fetch("http://localhost:8080/auth/registro", {
        method: "POST",
        headers: { "Content-Type": "application/json" },
        body: JSON.stringify({ email, password: pass })
    });

    if (resp.ok) {
        alert("Usuario registrado");
        modal.style.display = "none";
    } else {
        alert("Error creando usuario");
    }
});