<template>
<div>
  <section class="HelpModal">
    <div class="modal fade" id="HelpModal" tabindex="-1" aria-labelledby="HelpModalLabel" aria-hidden="true">
      <div class="modal-dialog">
        <div class="modal-content">
          <div class="modal-header justify-content-center">
            <h5 class="modal-title" id="HelpModalLabel">도움말</h5>
          </div>
          <div class="modal-body">
            <div class="help-content">
                <br> 조작법<br> 방향키 : 
                <i class="fas fa-long-arrow-alt-up"></i>&nbsp;
                <i class="fas fa-long-arrow-alt-left"></i>&nbsp;
                <i class="fas fa-long-arrow-alt-down"></i>&nbsp; 
                <i class="fas fa-long-arrow-alt-right"></i>
                &nbsp;&nbsp;또는 &nbsp; W A S D
                 <br> 캐릭터 행동 : 1 2 3
                <br> 주변의 사물을 클릭하여 방명록, 랭킹 등을 볼 수 있습니다.
                <br> 각 교실의 칠판의 클릭하면 교육 영상 시청 후 게임이 시작됩니다.
                <br> 게임 내에서도 사물을 클릭함으로써 미션을 수행할 수 있습니다.
                <br><br>
                
                메뉴 설명<br> 
                기본 맵, Back : 원래 게임 화면으로 돌아옵니다.<br>
                쪽지 : 원하는 상대와 쪽지를 주고 받을 수 있습니다.<br>
                마이페이지 : 내 정보 수정, 교육 이수 내역을 확인할 수 있습니다.<br><br> 
                전체 채팅 : 모든 유저와 대화 가능합니다.<br>
                <br>
            </div>
          </div>
          <div class="modal-footer justify-content-center">
            <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">닫기</button>
          </div>
        </div>
      </div>
    </div>
  </section>
</div>
</template>

<script>
export default {
  
};
</script>

<style>
    
</style>