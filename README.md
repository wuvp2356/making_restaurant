# making_restaurant
<!-- #入れ忘れてた。。。 -->
<!---->
<!-- markdownっていう記法でこんな感じで書くと右みたいなのが作れる -->
## 基本的なgitコマンド

- [git status](#git_status)
- [git add](#git_add)
- [git commit](#git_commit)
- [git push](#git_push)
- [git pull](#git_pull)
- [git branch](#git_branch)
- [git switch](#git_switch)
- [git log](#git_log)

### git_status

gitの状態を確認する

```sh
git status
```

### git_add

変更をステージングする

```sh
# 全ての変更をステージングする
git add .
# ファイルの変更をステージングする
git add file_name
# ディレクトリ下の変更をステージングする
git add dirctory_name
```
### git_commit

ステージングした内容をコミットする
```sh
# ステージングした内容をコミットする
git commit -m 'コミットメッセージ'
# 直前のコミットメッセージを変更する
git commit --amend -m '新しいコミットメッセージ'
```

### git_push

コミットした内容をpushする

```sh
# pushする
git push
# branchを指定してpushする
git push -u origin branch_name
```

### git_pull

remote repositoryから変更を取得する

```sh
# remote repositoryから変更を取得する
git pull
```

### git_branch

gitのbranchを一覧表示する

```sh
# ローカルにあるbranchを一覧表示する
git branch
# remote repositoryのものも含めて一覧表示する
git branch -a
```

### git_switch

ブランチを切り替える

```sh
# 新しいbranchを作成する
git switch -c branch_name
# branchを切り替える
git switch branch_name
```

### git_log

git logを確認する  
- j: 下
- k: 上
- G: 一番下
- gg: 一番上
- q: git logを抜ける

```sh
# git logを確認する
git log
```