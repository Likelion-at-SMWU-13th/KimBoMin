document.getElementById('loginForm').addEventListener('submit', function (e) {
    e.preventDefault();
  
    const username = document.getElementById('username').value;
    const password = document.getElementById('password').value;
    const message = document.getElementById('loginMessage');
  
    if (username === 'admin' && password === '1234') {
      message.style.color = 'green';
      message.textContent = '로그인 성공! 메인 페이지로 이동합니다';
      setTimeout(() => {
        window.location.href = 'index.html';
      }, 1000);
    } else {
      message.style.color = '#d9534f';
      message.textContent = '아이디 또는 비밀번호가 올바르지 않습니다.';
    }
  });
  