document.getElementById("contactForm").addEventListener("submit", function (e) {
    e.preventDefault();
    alert("문의가 성공적으로 제출되었습니다! 감사합니다 😊");
    this.reset();
  });
  
  const sections = document.querySelectorAll('.section');
  let currentSection = 0;
  let isScrolling = false;
  
  window.addEventListener('wheel', (e) => {
    if (isScrolling) return;
  
    if (e.deltaY > 0 && currentSection < sections.length - 1) {
      currentSection++;
    } else if (e.deltaY < 0 && currentSection > 0) {
      currentSection--;
    } else {
      return;
    }
  
    isScrolling = true;
    sections[currentSection].scrollIntoView({ behavior: 'smooth' });
  
    setTimeout(() => {
      isScrolling = false;
    }, 1000);
  });
  