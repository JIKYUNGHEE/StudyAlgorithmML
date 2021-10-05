var isMobile = {
    Android: function () {
        return navigator.userAgent.match(/Anroid/i) == null ? false : true;
    },
    iOS: function () {
        return navigator.userAgent.match(/iPhone|iPad|iPod/i) == null ? false : true;
    },
    any: function () {
        return (isMobile.Android() || isMobile.iOS())
    }
}

//입력된 링크를 전달하는 bridge 함수
function ontLink() {
    if(isMobile.any()) {
        android.brige.outLink(link);
    }
    if(isMobile.iOS()) {
        webkit.messageHandlers.outLink.postMessage(link);
    }
}

//back button 클릭 bridge 함수
function back() {
    if(isMobile.any()) {
        if(isMobile.Android()) {
            android.bridge.back(true);
        }

        if(isMobile.iOS()) {
            webkit.messageHandlers.back.postMessage(true);
        }
    }
}