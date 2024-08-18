# 운빨포켓몬(Pokemon_luck)

## 🎮 **게임 개요**

 : Pokemon Luck은 플레이어가 사냥터에서 포켓몬을 탐색하고 포획하는 콘솔 기반의 텍스트 게임입니다. 플레이어는 사냥을 진행하며 여러 종류의 포켓몬과 마주하고, 적절한 포켓몬 볼을 사용해 포켓몬을 포획해야 합니다.

## **🚀 게임 시작**

게임을 시작하면 시작 화면과 함께 메인 화면이 표시됩니다. 메인 화면에서 아래의 메뉴를 선택할 수 있습니다.

1. **📊 내 정보 확인**: 현재 플레이어의 골드, 보유한 포켓몬 볼 종류 및 개수, 포획한 포켓몬의 기록을 확인할 수 있습니다.
2. **🏪 상점**: 보유중인 골드로 포켓몬 볼을 구입할 수 있습니다.
3. **🏹 사냥**: 포켓몬볼을 사용해 포켓몬을 포획하여 골드를 얻을 수 있습니다.
4. **🚪 프로그램 종료**: 플레이어 정보를 저장한 후 게임을 종료합니다.

## **🕹️ 게임 내용**

### **1. 📈 내 정보 확인**

- **💰 현재 골드**: 플레이어가 보유한 골드의 양을 표시합니다.
- **🎒 보유한 포켓몬 볼 종류 및 개수**: 플레이어가 보유한 포켓몬 볼의 종류와 각각의 개수를 표시합니다.
- **📝 포획한 포켓몬 기록**: 플레이어가 현재까지 포획한 포켓몬의 이름과 등급을 확인할 수 있습니다.

### **2. 🛒 상점 이용**

- 상점에서는 포켓몬 볼을 구매할 수 있으며, 구매 후에는 플레이어의 골드가 차감됩니다.
  
- **포켓몬 볼 종류 및 가격**
  
    : 상점에서 구매할 수 있는 포켓몬 볼의 가격은 아래와 같습니다.
 
    - **몬스터볼**: 10 G
    - **슈퍼볼**: 50 G
    - **하이퍼볼**: 100 G
    - **마스터볼**: 200 G

### **3. 📊 포켓몬 정보 및 포획 확률**

: 포켓몬의 등급에 따라 포획 난이도가 달라집니다. 또한 각 등급의 포획 성공 확률은 사용한 포켓몬 볼의 종류에 따라 다릅니다.
<details>
<summary>1. 일반 (Normal) 등급 포켓몬</summary>

- **종류 : 8가지**
    - 피카츄, 라이츄, 파이리, 꼬북이, 버터풀, 야도란, 피죤투, 또가스
- **포획 확률**
    - **몬스터볼**: 50%
    - **슈퍼볼**: 70%
    - **하이퍼볼**: 100%
    - **마스터볼**: 100%
- **포획시 얻는 골드 : 20 G**

</details>

<details>
<summary>2. 유니크 (Unique) 등급 포켓몬</summary>

- **종류 : 6가지**
    - 리자몽, 이상해풀, 망냐뇽, 잠만보, 롱스톤, 뮤
- **포획 확률**
    - **몬스터볼**: 10%
    - **슈퍼볼**: 50%
    - **하이퍼볼**: 75%
    - **마스터볼**: 100%
- **포획시 얻는 골드 : 80 G**

</details>

<details>
<summary>3. 전설 (Legendary) 등급 포켓몬</summary>

- **종류 : 3가지**
    - 디아루가, 펄기아, 기라티나
- **포획 확률**
    - **몬스터볼**: 3%
    - **슈퍼볼**: 15%
    - **하이퍼볼**: 40%
    - **마스터볼**: 70%
- **포획시 얻는 골드 : 250G**

</details>

<details>
<summary>4. 신 (God) 등급 포켓몬</summary>

- **종류 : 1가지**
    - 아르세우스
- **포획 확률**
    - **몬스터볼**: 0.01%
    - **슈퍼볼**: 0.1%
    - **하이퍼볼**: 1%
    - **마스터볼**: 5%
- **포획 성공시 게임 종료.**

</details>

### 4. 🏹 사냥 진행

- **사냥 시작**
    - 총 3종류의 사냥터가 존재하고, 사냥터 등급 별 포켓몬의 등장 확률은 아래와 같습니다.
      
    - **1. 초급 사냥터**
        - 일반 포켓몬 :  70 %
        - 유니크 포켓몬 : 29 %
        - 전설 포켓몬 : 1%
          
    - **2. 중급 사냥터**
        - 일반 포켓몬 : 10 %
        - 유니크 포켓몬 : 50 %
        - 전설 포켓몬 : 39 %
        - 신 포켓몬 : 1 %
          
    - **3. 고급 사냥터**
        - 일반 포켓몬 : 1%
        - 유니크 포켓몬 : 10%
        - 전설 포켓몬 : 61%
        - 신 포켓몬 : 29%
    
    ⇒ 현재 자신이 보유한 몬스터볼의 개수와 종류를 고려하여 원하는 사냥터에 입장할 수 있습니다.
    
- **포켓몬 발견**
    - 사냥터에서 포켓몬을 발견하면, 현재 보유한 볼의 종류와 개수, 포획 확률을 고려해 포획을 진행하거나 도망갈 수 있습니다.
        
        🏃**도망 가기** : 사냥터에서 빠져나와 메인화면으로 이동합니다.
        
        🎯 **포획 시도:** 포켓몬 ****포획에 사용할 볼의 종류를 선택합니다.
        
        1. **실패**: 포획에 실패하면 사용한 볼은 소모되며, 포획에 성공할 때 까지 반복해 시도할 수 있습니다.
        2. **성공**: 포획에 성공하면 포켓몬의 등급에 따라 골드를 획득하고, 포켓몬이 플레이어의 포켓몬 목록에 추가됩니다
        

## **🏆 게임 목표**

- 신 등급 포켓몬 **아르세우스를 포획**하는 것이 이 게임의 최종 목표입니다.

## **⚠️ 주의 사항**

- 볼을 보유하지 않고, 볼을 구매할 최소한의 골드도 보유하지 않고 있다면 게임이 종료됩니다. 따라서 포켓몬 볼과 골드가 모두 소모되지 않도록 신중하게 사냥을 진행하세요.
- 신 등급 포켓몬 아르세우스의 포획 확률은 매우 낮습니다. 아르세우스를 만났다면 가지고 있는 포켓몬볼의 등급과 개수를 고려해 무리한 사냥을 삼가세요.
