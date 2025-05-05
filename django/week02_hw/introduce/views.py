from django.shortcuts import render

# Create your views here.

def hello_view(request):
    context = {
        'name': '김보민',
        'mbti' : 'ISTP',
        'hobby' : '여행'
    }
    return render(request, 'introduce/hello.html', context)