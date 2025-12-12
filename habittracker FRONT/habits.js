document.addEventListener("DOMContentLoaded", () => {

    const habitContainer = document.getElementById("habitos");
    const addHabitBtn = document.getElementById("botonHabito");
    const newHabitInput = document.getElementById("nuevoHabito");
    const newDescInput = document.getElementById("descHabito");

    // verificar usuario logueado
    const userId = localStorage.getItem("userId");

    if (!userId) {
        alert("Tenés que iniciar sesión");
        window.location.href = "index.html";
        return;
    }

    // cargar hábitos
    async function loadHabits() {
        habitContainer.innerHTML = "";

        const res = await fetch(`http://localhost:8080/api/usuarios/usuario/habito/${userId}`);
        const habits = await res.json();

        console.log("HABITOS:", habits);

        habits.forEach(h => {
            const div = document.createElement("div");
            div.classList.add("habit");

div.innerHTML = `
    <input type="checkbox" ${h.completado ? "checked" : ""} data-id="${h.id}">
    <span class="habit-text">${h.nombre}</span>
`;


            ;

            habitContainer.appendChild(div);
        });

        document.querySelectorAll("input[type='checkbox']").forEach(box => {
            box.addEventListener("change", () => toggleHabit(box));
        });
    }

    // agregar hábito
    addHabitBtn.addEventListener("click", async () => {
        const nombre = newHabitInput.value.trim();
        const desc = newDescInput.value.trim();

        if (!nombre) return alert("Nombre vacío");

        await fetch(`http://localhost:8080/api/usuarios/usuario/habito/${userId}`, {
            method: "POST",
            headers: { "Content-Type": "application/json" },
            body: JSON.stringify({ nombre, descripcion: desc })
        });

        newHabitInput.value = "";
        newDescInput.value = "";

        loadHabits();
    });

    // marcar hábito
    async function toggleHabit(checkbox) {
        const id = checkbox.getAttribute("data-id");

        await fetch(`http://localhost:8080/api/usuarios/usuario/habito/marcar/${id}`, {
            method: "PUT"
        });
    }

    loadHabits();
});

// CERRAR SESION
document.getElementById("logoutBtn").addEventListener("click", () => {
    localStorage.removeItem("userId");
    window.location.href = "index.html";
});

