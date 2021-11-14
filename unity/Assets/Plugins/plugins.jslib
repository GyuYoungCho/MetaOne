mergeInto(LibraryManager.library, {

  UnityObjectHook: function (str){
    _objectHook(Pointer_stringify(str));
  },
  UnityCharacterHook: function (str){
    _characterHook(Pointer_stringify(str));
  },
  UnityRoomHook: function (str){
    _roomHook(Pointer_stringify(str));
  },
  UnityEducationNameHook: function (str){
    _educationNameHook(Pointer_stringify(str));
  },
  UnityEducationTimeHook: function (str){
    _educationTimeHook(Pointer_stringify(str));
  },
  UnityEducationAuthHook: function (str){
    _educationAuthHook(Pointer_stringify(str));
  },
  
  

});