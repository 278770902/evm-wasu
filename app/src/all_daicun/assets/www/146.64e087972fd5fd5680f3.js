(window.webpackJsonp=window.webpackJsonp||[]).push([[146],{Gdj4:function(l,n,e){"use strict";e.r(n);var t=e("CcnG"),u=e("atIC"),o=e("0/uQ"),i=e("psW0"),a=e("lbwW"),r=e("O0u2"),d=e("B0rH"),s=e("1FC6"),c=e("dPDy"),p=e("gIcY"),g=function(){function l(l,n,e,t,u,o,i,a,r){this.app=l,this.activatedRoute=n,this.aumsService=e,this.adminUserService=t,this.utils=u,this.logger=o,this.editor=i,this.fb=a,this.platform=r,this.focus=!0,this.placeholder="请输入内容，GIF格式图片大小不能超过2M",this.showToolbar=!0,this.maxLength=Number.MAX_SAFE_INTEGER,this.title=this.activatedRoute.snapshot.paramMap.get("title"),this.page=this.activatedRoute.snapshot.paramMap.get("page");var d=this.activatedRoute.snapshot.paramMap.get("focus");this.focus=null==d||!!d;var s=this.activatedRoute.snapshot.paramMap.get("placeholder");s&&(this.placeholder=s);var c=this.activatedRoute.snapshot.paramMap.get("toolbar");c&&"false"===c&&(this.showToolbar=!1);var g=this.activatedRoute.snapshot.paramMap.get("content")||this.editor.content,m=this.activatedRoute.snapshot.paramMap.get("maxLength");m&&(this.maxLength=+m),this.formGroup=this.fb.group({editor:new p.e(g||"")})}return l.prototype.ngOnInit=function(){},l.prototype.onEditorCreated=function(l){this.focus&&l.focus(),l.getModule("toolbar").addHandler("image",this.imageHandler.bind(this)),this.quillInstance=l},l.prototype.submit=function(){var n=this.formGroup.get("editor");n.valid?(this.logger.debug(l.TAG+" submit() 输入的内容："+n.value+" 标签："+n.value.match(/<\/?p>/g)),this.app.appChannel.emit({type:s.a.EDITORCONTENT,page:this.page,data:n.value}),this.app.back()):n.errors&&n.errors.maxLengthError?this.utils.toast("fail","内容不能超过"+this.maxLength+"字！"):n.errors&&(n.errors.required||n.errors.requiredError)&&this.utils.toast("fail","内容不能为空！")},l.prototype.imageHandler=function(){var l=this,n=document.createElement("input");n.setAttribute("type","file"),n.setAttribute("accept","image/gif,image/jpeg,image/jpg,image/png,image/svg"),!this.platform.is("ios")&&n.setAttribute("capture","camera"),n.setAttribute("multiple","multiple"),n.classList.add("ql-image"),n.addEventListener("change",function(e){l.upload(n.files)}),n.click()},l.prototype.upload=function(n){var e=this;n&&n.length>9?this.utils.toast("fail","图片上传每次不能超过9张！"):(this.utils.toast("loading","图片上传中...",0),Object(o.a)(n).pipe(Object(i.a)(function(l){return e.aumsService.createImage({name:"手机端上传",imgFile:l,userId:e.adminUserService.user.id.toString()})})).subscribe(function(n){if("success"===n.status&&n.data&&"finish"===n.data.state)try{var t=n.data.data.data.url,u=e.quillInstance.getSelection(!0),o=u.index+u.length;e.quillInstance.insertEmbed(o,"image",t,"user"),e.quillInstance.insertText(o+1,"\n","user"),e.quillInstance.setSelection(o+2)}catch(i){e.logger.error(l.TAG+" upload() 上传图片获取图片地址报错: "+i.message),e.logger.error(l.TAG+" upload() 上传图片接口返回数据: "+JSON.stringify(n)),e.utils.toast("fail","图片上传失败，请稍后再试！")}},function(n){e.logger.error(l.TAG+" upload() 上传图片报错: "+n.message),e.logger.error(l.TAG+" upload() 请求接口地址: "+n.url),e.utils.toast("fail","图片上传失败，请稍后再试！")},function(){e.utils.toast("success","图片上传成功",500)}))},l.TAG="EditorComponent",l}(),m=function(){return function(){}}(),h=e("pMnS"),f=e("MKJQ"),v=e("jy/b"),b=e("lnN7"),R=e("Z+Mw"),C=e("ZYjt"),E=e("Ip0R"),y=e("ZYCi"),q=e("U3FM"),I=t["ɵcrt"]({encapsulation:0,styles:[[".ql-toolbar.ql-snow{border-width:0 0 1px;position:absolute;top:0;width:100%;height:40px;z-index:10}  .ql-container.ql-snow{border-width:0;padding-top:41px}  .ql-editor.ql-blank::before{font-style:normal}  .ql-editor p{color:var(--text-color-primary);font-size:var(--font-size-sm);line-height:var(--line-height-sm);white-space:normal}  .ql-editor p img{display:block}.hide-toolbar[_ngcontent-%COMP%]     .ql-container.ql-snow{padding-top:0}"]],data:{}});function N(l){return t["ɵvid"](0,[(l()(),t["ɵeld"](0,0,null,null,15,"ion-header",[["class","ion-no-border"]],null,null,null,f.ub,f.t)),t["ɵdid"](1,49152,null,0,v.C,[t.ChangeDetectorRef,t.ElementRef,t.NgZone],null,null),(l()(),t["ɵeld"](2,0,null,0,13,"ion-toolbar",[["mode","ios"]],null,null,null,f.bc,f.ab)),t["ɵdid"](3,49152,null,0,v.Ab,[t.ChangeDetectorRef,t.ElementRef,t.NgZone],{mode:[0,"mode"]},null),(l()(),t["ɵeld"](4,0,null,0,3,"ion-buttons",[["slot","start"]],null,null,null,f.gb,f.f)),t["ɵdid"](5,49152,null,0,v.m,[t.ChangeDetectorRef,t.ElementRef,t.NgZone],null,null),(l()(),t["ɵeld"](6,0,null,0,1,"ion-icon",[["name","chevron-back-outline"]],null,[[null,"click"]],function(l,n,e){var t=!0;return"click"===n&&(t=!1!==l.component.app.back()&&t),t},f.vb,f.u)),t["ɵdid"](7,49152,null,0,v.D,[t.ChangeDetectorRef,t.ElementRef,t.NgZone],{name:[0,"name"]},null),(l()(),t["ɵeld"](8,0,null,0,2,"ion-title",[],null,null,null,f.Zb,f.Y)),t["ɵdid"](9,49152,null,0,v.yb,[t.ChangeDetectorRef,t.ElementRef,t.NgZone],null,null),(l()(),t["ɵted"](10,0,["",""])),(l()(),t["ɵeld"](11,0,null,0,4,"ion-buttons",[["slot","end"]],null,null,null,f.gb,f.f)),t["ɵdid"](12,49152,null,0,v.m,[t.ChangeDetectorRef,t.ElementRef,t.NgZone],null,null),(l()(),t["ɵeld"](13,0,null,0,2,"ion-button",[["fill","clear"]],null,[[null,"click"]],function(l,n,e){var t=!0;return"click"===n&&(t=!1!==l.component.submit()&&t),t},f.fb,f.e)),t["ɵdid"](14,49152,null,0,v.l,[t.ChangeDetectorRef,t.ElementRef,t.NgZone],{fill:[0,"fill"]},null),(l()(),t["ɵted"](-1,0,["确定"])),(l()(),t["ɵeld"](16,0,null,null,32,"ion-content",[],null,null,null,f.ob,f.n)),t["ɵdid"](17,49152,null,0,v.v,[t.ChangeDetectorRef,t.ElementRef,t.NgZone],null,null),(l()(),t["ɵeld"](18,0,null,0,30,"form",[["novalidate",""]],[[2,"ng-untouched",null],[2,"ng-touched",null],[2,"ng-pristine",null],[2,"ng-dirty",null],[2,"ng-valid",null],[2,"ng-invalid",null],[2,"ng-pending",null]],[[null,"submit"],[null,"reset"]],function(l,n,e){var u=!0;return"submit"===n&&(u=!1!==t["ɵnov"](l,20).onSubmit(e)&&u),"reset"===n&&(u=!1!==t["ɵnov"](l,20).onReset()&&u),u},null,null)),t["ɵdid"](19,16384,null,0,p.B,[],null,null),t["ɵdid"](20,540672,null,0,p.h,[[8,null],[8,null]],{form:[0,"form"]},null),t["ɵprd"](2048,null,p.b,null,[p.h]),t["ɵdid"](22,16384,null,0,p.p,[[4,p.b]],null,null),(l()(),t["ɵeld"](23,0,null,null,25,"quill-editor",[["formControlName","editor"]],[[2,"hide-toolbar",null],[2,"ng-untouched",null],[2,"ng-touched",null],[2,"ng-pristine",null],[2,"ng-dirty",null],[2,"ng-valid",null],[2,"ng-invalid",null],[2,"ng-pending",null]],[[null,"onEditorCreated"]],function(l,n,e){var t=!0;return"onEditorCreated"===n&&(t=!1!==l.component.onEditorCreated(e)&&t),t},b.b,b.a)),t["ɵdid"](24,4898816,null,0,R.b,[t.ElementRef,C.b,E.DOCUMENT,t.PLATFORM_ID,t.Renderer2,t.NgZone,R.a],{placeholder:[0,"placeholder"],maxLength:[1,"maxLength"]},{onEditorCreated:"onEditorCreated"}),t["ɵprd"](1024,null,p.l,function(l){return[l]},[R.b]),t["ɵprd"](1024,null,p.m,function(l){return[l]},[R.b]),t["ɵdid"](27,671744,null,0,p.g,[[3,p.b],[6,p.l],[8,null],[6,p.m],[2,p.z]],{name:[0,"name"]},null),t["ɵprd"](2048,null,p.n,null,[p.g]),t["ɵdid"](29,16384,null,0,p.o,[[4,p.n]],null,null),(l()(),t["ɵeld"](30,0,null,0,18,"div",[["quill-editor-toolbar",""]],[[2,"hidden",null]],null,null,null,null)),(l()(),t["ɵeld"](31,0,null,null,15,"span",[["class","ql-formats"]],null,null,null,null,null)),(l()(),t["ɵeld"](32,0,null,null,0,"button",[["class","ql-bold"]],[[8,"title",0]],null,null,null,null)),(l()(),t["ɵeld"](33,0,null,null,0,"button",[["class","ql-italic"]],[[8,"title",0]],null,null,null,null)),(l()(),t["ɵeld"](34,0,null,null,12,"select",[["class","ql-align"]],[[8,"title",0]],null,null,null,null)),(l()(),t["ɵeld"](35,0,null,null,2,"option",[["selected",""]],null,null,null,null,null)),t["ɵdid"](36,147456,null,0,p.s,[t.ElementRef,t.Renderer2,[8,null]],null,null),t["ɵdid"](37,147456,null,0,p.A,[t.ElementRef,t.Renderer2,[8,null]],null,null),(l()(),t["ɵeld"](38,0,null,null,2,"option",[["value","center"]],null,null,null,null,null)),t["ɵdid"](39,147456,null,0,p.s,[t.ElementRef,t.Renderer2,[8,null]],{value:[0,"value"]},null),t["ɵdid"](40,147456,null,0,p.A,[t.ElementRef,t.Renderer2,[8,null]],{value:[0,"value"]},null),(l()(),t["ɵeld"](41,0,null,null,2,"option",[["value","right"]],null,null,null,null,null)),t["ɵdid"](42,147456,null,0,p.s,[t.ElementRef,t.Renderer2,[8,null]],{value:[0,"value"]},null),t["ɵdid"](43,147456,null,0,p.A,[t.ElementRef,t.Renderer2,[8,null]],{value:[0,"value"]},null),(l()(),t["ɵeld"](44,0,null,null,2,"option",[["value","justify"]],null,null,null,null,null)),t["ɵdid"](45,147456,null,0,p.s,[t.ElementRef,t.Renderer2,[8,null]],{value:[0,"value"]},null),t["ɵdid"](46,147456,null,0,p.A,[t.ElementRef,t.Renderer2,[8,null]],{value:[0,"value"]},null),(l()(),t["ɵeld"](47,0,null,null,1,"span",[["class","ql-formats"]],null,null,null,null,null)),(l()(),t["ɵeld"](48,0,null,null,0,"button",[["class","ql-image"]],null,null,null,null,null))],function(l,n){var e=n.component;l(n,3,0,"ios"),l(n,7,0,"chevron-back-outline"),l(n,14,0,"clear"),l(n,20,0,e.formGroup),l(n,24,0,t["ɵinlineInterpolate"](1,"",e.placeholder,""),t["ɵinlineInterpolate"](1,"",e.maxLength,"")),l(n,27,0,"editor"),l(n,39,0,"center"),l(n,40,0,"center"),l(n,42,0,"right"),l(n,43,0,"right"),l(n,45,0,"justify"),l(n,46,0,"justify")},function(l,n){var e=n.component;l(n,10,0,e.title),l(n,18,0,t["ɵnov"](n,22).ngClassUntouched,t["ɵnov"](n,22).ngClassTouched,t["ɵnov"](n,22).ngClassPristine,t["ɵnov"](n,22).ngClassDirty,t["ɵnov"](n,22).ngClassValid,t["ɵnov"](n,22).ngClassInvalid,t["ɵnov"](n,22).ngClassPending),l(n,23,0,!e.showToolbar,t["ɵnov"](n,29).ngClassUntouched,t["ɵnov"](n,29).ngClassTouched,t["ɵnov"](n,29).ngClassPristine,t["ɵnov"](n,29).ngClassDirty,t["ɵnov"](n,29).ngClassValid,t["ɵnov"](n,29).ngClassInvalid,t["ɵnov"](n,29).ngClassPending),l(n,30,0,!e.showToolbar),l(n,32,0,"加粗"),l(n,33,0,"斜体"),l(n,34,0,"对齐")})}function M(l){return t["ɵvid"](0,[(l()(),t["ɵeld"](0,0,null,null,1,"app-editor",[],null,null,null,N,I)),t["ɵdid"](1,114688,null,0,g,[u.a,y.a,a.a,r.a,d.a,q.b,c.a,p.d,v.Ib],null,null)],function(l,n){l(n,1,0)},null)}var w=t["ɵccf"]("app-editor",g,M,{},{},[]);e.d(n,"EditorModuleNgFactory",function(){return A});var A=t["ɵcmf"](m,[],function(l){return t["ɵmod"]([t["ɵmpd"](512,t.ComponentFactoryResolver,t["ɵCodegenComponentFactoryResolver"],[[8,[h.a,w]],[3,t.ComponentFactoryResolver],t.NgModuleRef]),t["ɵmpd"](4608,E.NgLocalization,E.NgLocaleLocalization,[t.LOCALE_ID,[2,E["ɵangular_packages_common_common_a"]]]),t["ɵmpd"](4608,v.c,v.c,[t.NgZone,t.ApplicationRef]),t["ɵmpd"](4608,v.Fb,v.Fb,[v.c,t.ComponentFactoryResolver,t.Injector]),t["ɵmpd"](4608,v.Jb,v.Jb,[v.c,t.ComponentFactoryResolver,t.Injector]),t["ɵmpd"](4608,p.y,p.y,[]),t["ɵmpd"](4608,p.d,p.d,[]),t["ɵmpd"](1073742336,E.CommonModule,E.CommonModule,[]),t["ɵmpd"](1073742336,y.n,y.n,[[2,y.s],[2,y.m]]),t["ɵmpd"](1073742336,v.Cb,v.Cb,[]),t["ɵmpd"](1073742336,R.c,R.c,[]),t["ɵmpd"](1073742336,p.x,p.x,[]),t["ɵmpd"](1073742336,p.i,p.i,[]),t["ɵmpd"](1073742336,p.u,p.u,[]),t["ɵmpd"](1073742336,m,m,[]),t["ɵmpd"](1024,y.k,function(){return[[{path:"",component:g}]]},[])])})}}]);