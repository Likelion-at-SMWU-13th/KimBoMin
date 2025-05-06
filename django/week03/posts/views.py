from django.shortcuts import redirect, render, get_object_or_404
from django.http import HttpResponse, JsonResponse

from django.views.generic import ListView
from .models import Post
from. forms import PostBasedForm, PostModelForm

# Create your views here.

#3주차 장고 세미나
def post_form_view(request):
    if request.method == "GET":
        form = PostBasedForm()
        context = {'form' : form}
        return render(request, 'posts/post_form2.html', context)
    else:
        return redirect('index')
    
def post_create_form_view(request):
    if request.method == "GET":
        form = PostModelForm()
        context = {'form' : form}
        return render(request, 'posts/post_form2.html', context)
    
    else : 
        form = PostModelForm(request.POST,request.FILES)

        if form.is_valid():
            Post.objects.create(
                image = form.cleaned_data['image'],
                content = form.cleaned_data['content'],
                writer = request.user
            )
        else:
            print(form.errors)
            return redirect('posts:post-new')
        return redirect('index')
        
#2주차 세미나
def index(request):
    return render(request, 'index.html')

def post_list_view(request):
    return render(request, 'posts/post_list.html')

def post_detail_view(request, id):
    post = Post.objects.get(id=id)
    context = {'post' : post}
    return render(request, 'posts/post_detail.html', context)

def post_create_view(request):
    return render(request, 'posts/post_form.html')

#기존에 작성해둔 post_update_view를 수정
def post_update_view(request, id):
    post = Post.objects.get(id=id)
    if request.method == "GET":
        form = PostModelForm(instance=post)
        context = {'form': form, 'post': post}
        return render(request, 'posts/post_update.html', context)

    else:  
        form = PostModelForm(request.POST, request.FILES, instance=post)
        if form.is_valid():
            form.save()
            return redirect('posts:post-detail', id=id) 
        else:
            print(form.errors)
            return render(request, 'posts/post_update.html', {'form': form})

def post_delete_view(request, id):
    post = get_object_or_404(Post, id=id)
    
    if request.method == "POST":
        post.delete()
        return redirect('index')  # 삭제 후 홈 화면으로 리다이렉트

    context = {'post': post}
    return render(request, 'posts/post_confirm_delete.html', context)

class class_view(ListView):
    model = Post
    template_name='cbv_view.html'


def url_view(request):
    data = {'code':'001','msg':'OK'}
    return HttpResponse('<h1>url_views</h1>')

def url_parameter_view(request, username):
    print('url_parameter_view()')
    print(f'username: {username}')
    print(f'request.GET: {request.GET}')
    return HttpResponse(username)

def function_view(request):
    print(f'request.method:{request.method}')
    if request.method == "GET":
        print(f'request.GET: {request.GET}')
    elif request.method == "POST":
        print(f'request.POST: {request.POST}')
    return render(request, 'view.html')

