mergeInto(LibraryManager.library, {

  UnityObjectHook: function (obj){
    _objectHook(Pointer_stringify(obj));
  },
  UnityCharacterHook: function (number){
    _characterHook(number);
  },
  UnityRoomHook: function (roomName){
    _roomHook(Pointer_stringify(roomName));
  },
  UnityEducationNameHook: function (str){
    _educationNameHook(Pointer_stringify(str));
  },
  UnityEducationTimeHook: function (time)){
    _educationTimeHook(time);
  },
  UnityEducationAuthHook: function (auth){
    _educationAuthHook(auth);
  },
  
  

});