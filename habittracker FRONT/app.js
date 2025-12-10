
    document.getElementById("botonLogin").addEventListener("click", async () => {
        const email = document.querySelector("input[name='email']").value;
        const pass = document.querySelector("input[name='pass']").value;

        // Armamos el body para el login
        const body = {
            email: email,
            password: pass
        };

        try {
            const response = await fetch("http://localhost:8080/login", {
                method: "POST",
                headers: {
                    "Content-Type": "application/json"
                },
                body: JSON.stringify(body)
            });

            if (response.ok) {
                //leo el userId que me da el backend
                const userId = await response.json();

                //ahora si lo guardo
                localStorage.setItem("userId", userId);

                // Redirigir a otra página
                window.location.href = "home.html";  
            } else {
                alert("Email o contraseña incorrectos");
            }
        } catch (error) {
            console.error("Error:", error);
            alert("No se pudo conectar con el servidor");
        }
    });
