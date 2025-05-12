from django.shortcuts import render
from django.views.generic import ListView, DetailView, CreateView, UpdateView, DeleteView
from django.contrib.auth.mixins import LoginRequiredMixin
from django.urls import reverse_lazy
from .models import BoardPost
from .forms import BoardPostForm

# 게시글 목록
class BoardListView(ListView):
    model = BoardPost
    template_name = 'board/board_list.html'

# 게시글 상세
class BoardDetailView(DetailView):
    model = BoardPost
    template_name = 'board/board_detail.html'

# 게시글 작성
class BoardCreateView(LoginRequiredMixin, CreateView):
    model = BoardPost
    form_class = BoardPostForm
    template_name = 'board/board_form.html'
    success_url = reverse_lazy('board:list')

    def form_valid(self, form):
        form.instance.writer = self.request.user
        return super().form_valid(form)

# 게시글 수정
class BoardUpdateView(LoginRequiredMixin, UpdateView):
    model = BoardPost
    form_class = BoardPostForm
    template_name = 'board/board_form.html'
    success_url = reverse_lazy('board:list')

# 게시글 삭제
class BoardDeleteView(LoginRequiredMixin, DeleteView):
    model = BoardPost
    template_name = 'board/board_confirm_delete.html'
    success_url = reverse_lazy('board:list')
