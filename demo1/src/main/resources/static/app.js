document.addEventListener('DOMContentLoaded', () => {



    /**
     * Elementos principais da interface.
     */
    const loginView = document.getElementById('login-view');
    const qrView = document.getElementById('qr-view');
    const loginForm = document.getElementById('loginForm');
    const logoutBtn = document.getElementById('logoutBtn');
    const loginError = document.getElementById('loginError');
    const qrImage = document.getElementById('qrImage');
    const qrForm = document.getElementById('qrForm');

    /**
     * Verifica se o usuário está autenticado ao carregar a página.
     * Se estiver autenticado, mostra a tela de QR Code.
     * Caso contrário, exibe a tela de login.
     */
    async function checkAuth() {
        try {
            const res = await fetch('/me');
            if (res.ok) {
                showQrView();
            } else {
                showLoginView();
            }
        } catch (error) {
            console.error('Error checking authentication:', error);
            showLoginView();
        }
    }

    function showLoginView() {
        loginView.style.display = 'block';
        qrView.style.display = 'none';
    }

    function showQrView() {
        loginView.style.display = 'none';
        qrView.style.display = 'block';
    }
    console.log('SCript fully loaded');

    /**
     * Lida com o envio do formulário de login.
     * Envia os dados do usuário para o servidor e, se o login for bem-sucedido, exibe a tela de QR Code.
     */
    loginForm.addEventListener('submit', async (e) => {
        console.log('DOM fully loaded');
        e.preventDefault();
        loginError.textContent = '';
        const username = document.getElementById('username').value;
        const password = document.getElementById('password').value;
        try {
            const res = await fetch('/login', {
                method: 'POST',
                headers: { 'Content-Type': 'application/json' },
                body: JSON.stringify({ username, password })
            });
            if (res.ok) {
                console.log('Login bem-sucedido para o usuário:', username);
                showQrView();
            } else {
                const msg = await res.text();
                loginError.textContent = msg || 'Login failed';
            }
        } catch (error) {
            loginError.textContent = 'An error occurred during login.';
            console.error('Login error:', error);
        }
    });

    /**
     * Lida com o envio do formulário para gerar QR Code.
     * Envia a URL para o servidor e exibe o QR Code gerado.
     */
    qrForm.addEventListener('submit', async (e) => {
        e.preventDefault();
        const url = document.getElementById('urlInput').value;
        try {
            const res = await fetch('/generate', {
                method: 'POST',
                headers: { 'Content-Type': 'application/json' },
                body: JSON.stringify({ url })
            });
            if (res.ok) {
                const blob = await res.blob();
                qrImage.src = URL.createObjectURL(blob);
                qrImage.style.display = 'block';
            } else {
                alert('Error generating QR code. Make sure you are logged in and the URL is valid.');
            }
        } catch (error) {
            alert('An unexpected error occurred while generating the QR code.');
            console.error('QR generation error:', error);
        }
    });

    /**
     * Lida com o clique no botão de logout.
     * Envia a solicitação de logout para o servidor e exibe a tela de login.
     */
    logoutBtn.addEventListener('click', async () => {
        try {
            const res = await fetch('/logout', { method: 'POST' });
            if (res.ok) {
                showLoginView();
            } else {
                alert('Error logging out.');
            }
        } catch (error) {
            alert('An unexpected error occurred during logout.');
            console.error('Logout error:', error);
        }
    });

    /**
     * Chama a função para verificar a autenticação ao carregar a página.
     */
    checkAuth();
});