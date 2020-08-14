
<h1 align="center">
  Do! Collabo
</h1>
<p align="center">

<p align="center">
 <img src="https://img.shields.io/badge/platform-iOS-9cf.svg">        <img src="https://img.shields.io/badge/Swift-5.2-orange">
 <p align="center">협업을 위한 이슈 관리 어플리케이션</p>
</p>

<p align="center">
<img src="https://github.com/corykim0829/issue-tracker-02/blob/dev/screenshots/full_darkmode.gif" width="240px"> <img src="https://github.com/corykim0829/issue-tracker-02/blob/dev/screenshots/full_lightmode.gif" width="240px"> 
</p>

<br>

### 상세 화면

<p align="center">
<img src="https://github.com/corykim0829/issue-tracker-02/blob/dev/screenshots/screen-1.png" width="200px"> <img src="https://github.com/corykim0829/issue-tracker-02/blob/dev/screenshots/screen-2.png" width="200px"> <img src="https://github.com/corykim0829/issue-tracker-02/blob/dev/screenshots/screen-2-2.png" width="200px"> <img src="https://github.com/corykim0829/issue-tracker-02/blob/dev/screenshots/screen-3.png" width="200px"> <img src="https://github.com/corykim0829/issue-tracker-02/blob/dev/screenshots/screen-4.png" width="200px"> <img src="https://github.com/corykim0829/issue-tracker-02/blob/dev/screenshots/screen-4-2.png" width="200px"> <img src="https://github.com/corykim0829/issue-tracker-02/blob/dev/screenshots/screen-6.png" width="200px"> <img src="https://github.com/corykim0829/issue-tracker-02/blob/dev/screenshots/screen-7.png" width="200px">
</p>
<br>

### TroubleShooting


#### Dynamic UICollectionViewCell

이슈를 표시하는 UICollectionViewCell에 레이블의 개수가 많아지면 동적으로 셀의 높이를 잡아줄 수 있도록 구현을 하기 위해 고민을 많이 했었습니다. UICollectionView를 사용하는 만큼 UICollectionViewLayout을 공부하여 구현하고 싶었지만, 시간이 부족했습니다. 그래서 DummyCell을 만들어서 높이를 계산해주는 방식을 사용해서 구현을 하였습니다. 블로그에 어떻게 접근하여 문제를 해결했는지 정리하였습니다. 

[UICollectionViewCell Dynamic Height, 동적 높이 구현하기 with Dummy Cell](https://corykim0829.github.io/ios/UICollectionViewCell-dynamic-height/#)

<br>

#### More Action Menu

`Issue Cell`과 `Label Cell`에서 더보기 버튼을 눌러 추가적인 액션을 사용자가 취할 수 있는 **메뉴 기능**을 구현하기 위해서 커스텀 뷰를 만들어서 구현하려고 생각했습니다. 그러나 추가적인 액션이 적지 않고 뷰를 사용하면 뷰의 액션 처리는 결국 뷰의 상위 객체인 뷰 컨트롤러가 처리하게 되어 해당 뷰 컨트롤러가 거대해진다고 판단하여 **커스텀 뷰 컨트롤러**를 구현하였습니다. 먼저 기본 동작을 처리할 `MoreViewController`를 구현하여 이를 상속하여 `IssueCellMoreViewController`, `LabelCellMoreViewController`를 구현하였습니다. 

<p align="center">
<img src="https://github.com/corykim0829/issue-tracker-02/blob/dev/screenshots/screen-2-2.png" width="200px"> <img src="https://github.com/corykim0829/issue-tracker-02/blob/dev/screenshots/screen-4-2.png" width="200px">
</p>

구현한 화면을 보면 알 수 있듯이, 이슈와 레이블의 옵션 메뉴 항목 개수가 다릅니다. 이를 처리하기 위해서 `MoreOptionButton` 을 생성하는 메소드와 이를 `moreView`라는 메뉴 뷰 요소를 관리하는 객체에 버튼을 추가하게 되면 `moreView` 내부의 stackView에 잘 추가되도록 구현하였습니다.

```swift
// MoreViewController.swift
func generateButton(
    title: String,
    target: Any?,
    action: Selector,
    for event: UIControl.Event) -> UIButton {
    let button = MoreOptionButton()
    button.setTitle(title, for: .normal)
    button.addTarget(target, action: action, for: event)
    return button
}

func addOptions(buttons: UIButton...) {
    moreView.addOptions(buttons: buttons)
}
```

지금보니 두가지 메소드를 분리할 필요없이 바로 생성하여 추가해주면 더 깔끔할 것 같아서 아래와 같이 수정해보았습니다.

```swift
func addOptions(
    title: String,
    target: Any?,
    action: Selector,
    for event: UIControl.Event) {
    let button = MoreOptionButton()
    button.setTitle(title, for: .normal)
    button.addTarget(target, action: action, for: event)
    moreView.addOption(button: button)
}
```

상속을 사용해서 구현하긴 했지만, SOLID 원칙을 많이 고려하지 못한 점이 아쉽다. 인터페이스로 분리하여 책임을 분리한다면 더 좋을 것 같다.

<br>

### Members

- BE 
    - [🐦 Dan](https://github.com/Hyune-c)
- iOS 
    - [🐝 Delma](https://github.com/delmaSong)
    - [🦊 Cory](https://github.com/corykim0829)

<br>

### Commits

```
[iOS] feat :rocket: index page 구현
    
커밋 내용, 자유롭게 작성
    
#3
close, fix, resolved: #2 (optional)
```

| 타입 | 이모지 | 설명 |
|--|--|--|
|feat|:fire: `:fire:`|새로운 기능 추가|
|fix|:wrench: `:wrench:`|버그 수정|
|docs|:pencil: `:pencil:`|문서 수정|
|refactor|:recycle: `:recycle:`|코드 리팩토링|
|style|:art: `:art:`|코드 포맷팅 (코드 변경이 없는 경우)|
|test|:white_check_mark: `:white_check_mark:` |테스트 코드 작성|
|chore|:package: `:package:`|소스 코드를 건들지 않는 작업|

<br>

### Branches

- dev : default branch 
    - be
    - dev-ios
    - be/feat
    - ios/feat

<br>

### Issues

```
이슈 제목
ex) 카드 추가 (라벨 BE, 프로젝트 추가)
```

- 대분류는 label 을 사용한다. (ex: BE, iOS, Feature, Refactor, Fix)
    - label에 있는 것은 제목에 쓰지 않는다
- 내용은 checkbox 사용을 지향한다.

<br>

### Documents

- [Swagger](http://52.79.81.75:8080/swagger-ui.html)
- [iOS Feature Requirements](https://docs.google.com/spreadsheets/d/1PS3qxyUZ9dthyNLMbDasInC9mER7WPdZ7khVixbp6ng/edit#gid=0)
- [Design Guide](https://github.com/codesquad-member-2020/issue-tracker-02/wiki/Design-Guide-Document)
