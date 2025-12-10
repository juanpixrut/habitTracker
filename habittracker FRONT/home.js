document.addEventListener("DOMContentLoaded", () => {

    const habitContainer = document.getElementById("habitos");
    const addHabitBtn = document.getElementById("botonHabito");
    const newHabitInput = document.getElementById("nuevoHabito");
    const newDescInput = document.getElementById("descHabito");

    // ID del usuario logueado
    const userId = localStorage.getItem("userId");

    if (!userId) {
        alert("No hay usuario logueado. Volviendo al login.");
        window.location.href = "index.html";
        return;
    }

    // Cargar hábitos
    async function loadHabits() {
        habitContainer.innerHTML = "";

        try {
            const res = await fetch(`http://localhost:8080/api/usuarios/usuario/habito/${userId}`);

            if (!res.ok) {
                console.error("Error al cargar hábitos:", res.status, await res.text());
                return;
            }

            const habits = await res.json();

            habits.forEach(h => {
                const div = document.createElement("div");
                div.classList.add("habit");

                div.innerHTML = `
                    <input type="checkbox" ${h.completado ? "checked" : ""} data-id="${h.id}">
                    <span>${h.nombre}</span>
                `;

                habitContainer.appendChild(div);
            });

            // listeners para checkboxes
            document.querySelectorAll("input[type='checkbox']").forEach(box => {
                box.addEventListener("change", () => toggleHabit(box));
            });

        } catch (err) {
            console.error("Error de red al cargar hábitos:", err);
        }
    }

    // Agregar hábito
    addHabitBtn.addEventListener("click", async () => {
        const nombre = newHabitInput.value.trim();
        const desc = newDescInput ? newDescInput.value.trim() : "";

        if (nombre === "") {
            alert("El nombre del hábito no puede estar vacío");
            return;
        }

        try {
            const res = await fetch(`http://localhost:8080/api/usuarios/usuario/habito/${userId}`, {
                method: "POST",
                headers: {"Content-Type": "application/json"},
                body: JSON.stringify({
                    nombre: nombre,
                    descripcion: desc
                })
            });

            if (!res.ok) {
                console.error("Error al agregar hábito:", res.status, await res.text());
                return;
            }

            newHabitInput.value = "";
            if (newDescInput) newDescInput.value = "";

            loadHabits();

        } catch (err) {
            console.error("Error de red al agregar hábito:", err);
        }
    });

    // Marcar hábito
    async function toggleHabit(checkbox) {
        const id = checkbox.getAttribute("data-id");

        try {
            const res = await fetch(`http://localhost:8080/habito/${id}/check`, {
                method: "PUT"
            });

            if (!res.ok) {
                console.error("Error al marcar hábito:", res.status, await res.text());
            }
        } catch (err) {
            console.error("Error de red al marcar hábito:", err);
        }
    }

    loadHabits();
});
