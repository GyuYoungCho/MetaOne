# 안전예방 메타몽

<br>

## 🤝 Branch

### {branch header}/{short description}

`ex) feat/login`

- branch header rule
  - master, dev, feat
    - master : 배포 가능한 상태의 브랜치
    - dev : 개발을 진행하는 브랜치
    - feat : 새로운 기능 개발 및 버그 수정이 필요할 때마다 develop 브랜치로부터 분기
  - 이슈 한 개 당 하나의 브랜치

<br>

## 🤝 Commit

### [역할] Commit타입: :이모지: 내용

`ex) [FE / BE] feat: :sparkles: 로그인 카카오 API 추가`

커밋 내용은 한글로 작성합니다.

| Commit 종류 | 이모지	|  Commit 타입| 
| -- | -- | -- | 
| 처음 구조 잡을 때 | 🎉  `:tada:` | init:| 
| 새로운 기능을 추가할 경우 | ✨`:sparkles:`	| feat:| 
| 버그를 고친 경우 | 🐛`:bug:`	| fix:| 
| 기능 수정 X, 위치 변경, 메소드 위치 변경, 등...	| 🔨 `:hammer:`	| refactor:| 
| 문서를 수정한 경우 | 📚 `:books:`| docs:| 
| CSS 등 사용자 UI 디자인 변경	| 🎨 `:art:`	| design:|
| 필요한 주석 추가 및 변경 | 💡 `:bulb:` | comment: |
| 충돌 해결 | :wrench: `:wrench:` | conflict: |
