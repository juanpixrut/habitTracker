document.addEventListener("DOMContentLoaded", () => {

    document.getElementById("botonLogin").addEventListener("click", async () => {
        console.log("CLICK LOGIN");

        const email = document.getElementById("emailLogin").value.trim();
        const pass = document.getElementById("passLogin").value.trim();

        if (!email || !pass) {
            alert("Ingresá email y contraseña");
            return;
        }

        try {
            const resp = await fetch("http://localhost:8080/login", {
                method: "POST",
                headers: { "Content-Type": "application/json" },
                body: JSON.stringify({ email, password: pass })
            });

            if (!resp.ok) {
                alert(await resp.text());
                return;
            }

            const userId = await resp.json();
            localStorage.setItem("userId", userId);

            window.location.href = "home.html";

        } catch (e) {
            alert("Error comunicando con el servidor");
        }
    });

});
