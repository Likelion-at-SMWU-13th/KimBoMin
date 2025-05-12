# Django Forms 정리

> 참고 문서: https://docs.djangoproject.com/en/5.2/topics/forms/

## 1. Form이란?

Django의 Form은 사용자로부터 입력을 받아 처리하고,  
HTML `<form>`을 자동 생성하고 유효성 검사 및 정리까지 도와주는 도구이다.

## 2. Form 클래스 기본 구조

```python
from django import forms

class ContactForm(forms.Form):
    name = forms.CharField(max_length=100)
    email = forms.EmailField()
    message = forms.CharField(widget=forms.Textarea)
```

- `forms.Form`을 상속받아 작성
- 각 필드는 사용자 입력값을 받을 수 있는 위젯 형태로 지정

## 3. 폼 처리 과정 (View)

```python
def contact_view(request):
    if request.method == 'POST':
        form = ContactForm(request.POST)
        if form.is_valid():
            # 처리 로직
            ...
    else:
        form = ContactForm()
    return render(request, 'contact.html', {'form': form})
```

## 4. Template에서 사용

```html
<form method="post">
  {% csrf_token %}
  {{ form.as_p }}
  <button type="submit">제출</button>
</form>
```

- `{{ form.as_p }}`: 각 필드를 `<p>` 단위로 렌더링

## 5. 유효성 검사

- `form.is_valid()` → 유효성 검사
- `form.cleaned_data` → 통과된 값(dict 형태)
- `form.errors` → 에러 정보

## 6. 기타 기능

- 다양한 위젯 설정 가능 (`widget=`)
- 파일 업로드는 `request.FILES`와 함께 사용
