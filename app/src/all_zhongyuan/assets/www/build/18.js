webpackJsonp([18],{818:function(l,n,e){"use strict";function t(l){return a["ɵvid"](0,[(l()(),a["ɵeld"](0,0,null,null,1,"page-tip",[],null,[[null,"refresh"]],function(l,n,e){var t=!0;if("refresh"===n){t=!1!==l.component.refreshPage()&&t}return t},_.d,_.b)),a["ɵdid"](1,311296,null,0,v.c,[O.c],{data:[0,"data"],show:[1,"show"]},{refreshPage:"refresh"})],function(l,n){var e=n.component;l(n,1,0,e.pageTipData,e.pageTipShow)},null)}function u(l){return a["ɵvid"](0,[(l()(),a["ɵeld"](0,0,null,null,3,"div",[],[[2,"active",null]],[[null,"click"]],function(l,n,e){var t=!0;if("click"===n){t=!1!==l.component.openColumnItem(l.context.$implicit)&&t}return t},null,null)),(l()(),a["ɵted"](-1,null,["\n    "])),(l()(),a["ɵeld"](2,0,null,null,0,"img",[],[[8,"src",4],[8,"alt",0]],null,null,null,null)),(l()(),a["ɵted"](-1,null,["\n  "]))],null,function(l,n){l(n,0,0,n.context.index===n.component.currentIndex);l(n,2,0,a["ɵinlineInterpolate"](1,"",n.context.$implicit.displayContent,""),a["ɵinlineInterpolate"](1,"",n.context.$implicit.name,""))})}function i(l){return a["ɵvid"](0,[(l()(),a["ɵeld"](0,0,null,null,10,"ion-header",[],null,null,null,null,null)),a["ɵdid"](1,16384,null,0,L.a,[V.a,a.ElementRef,a.Renderer,[2,E.a]],null,null),(l()(),a["ɵted"](-1,null,["\n  "])),(l()(),a["ɵeld"](3,0,null,null,6,"ion-navbar",[["class","toolbar"]],[[8,"hidden",0],[2,"statusbar-padding",null]],null,null,A.b,A.a)),a["ɵdid"](4,49152,null,0,M.a,[D.a,[2,E.a],[2,F.a],V.a,a.ElementRef,a.Renderer],null,null),(l()(),a["ɵted"](-1,3,["\n    "])),(l()(),a["ɵeld"](6,0,null,3,2,"ion-title",[],null,null,null,$.b,$.a)),a["ɵdid"](7,49152,null,0,U.a,[V.a,a.ElementRef,a.Renderer,[2,z.a],[2,M.a]],null,null),(l()(),a["ɵted"](8,0,["",""])),(l()(),a["ɵted"](-1,3,["\n  "])),(l()(),a["ɵted"](-1,null,["\n"])),(l()(),a["ɵted"](-1,null,["\n\n"])),(l()(),a["ɵeld"](12,0,null,null,8,"ion-list",[["class","scroll-content "],["style","margin-top: 5.5rem;"]],null,null,null,null,null)),a["ɵdid"](13,16384,null,0,J.a,[V.a,a.ElementRef,a.Renderer,q.a,B.l,G.a],null,null),(l()(),a["ɵted"](-1,null,["\n  "])),(l()(),a["ɵand"](16777216,null,null,1,null,t)),a["ɵdid"](16,16384,null,0,H.NgIf,[a.ViewContainerRef,a.TemplateRef],{ngIf:[0,"ngIf"]},null),(l()(),a["ɵted"](-1,null,["\n  "])),(l()(),a["ɵand"](16777216,null,null,1,null,u)),a["ɵdid"](19,802816,null,0,H.NgForOf,[a.ViewContainerRef,a.TemplateRef,a.IterableDiffers],{ngForOf:[0,"ngForOf"]},null),(l()(),a["ɵted"](-1,null,["\n"])),(l()(),a["ɵted"](-1,null,["\n\n"]))],function(l,n){var e=n.component;l(n,16,0,e.pageTipShow);l(n,19,0,e.cloumnsSet)},function(l,n){var e=n.component;l(n,3,0,a["ɵnov"](n,4)._hidden,a["ɵnov"](n,4)._sbPadding);l(n,8,0,e.columnName)})}Object.defineProperty(n,"__esModule",{value:!0});var a=e(1),o=(e(0),e(61),e(23)),r=e(142),d=e(194),s=e(27),c=e(26),p=e(42),f=e(94),g=e(132),m=e(56),h=function(){function l(l,n,e,t,u,i,a,o,d,s){this.navCtrl=l,this.navParams=n,this.logger=e,this.aumsApiProvider=t,this.utils=u,this.routeUtils=i,this.nativeJS=a,this.pageAuth=o,this.pageTipProvider=d,this.normalUser=s,this.navIsOverflow=!1,this.defaultIndex=0,this.normalUserStatusLevels=[r.a.Passed],this.columnName=this.navParams.get("name"),this.cId=+this.navParams.get("cId"),this.defaultIndex=+this.navParams.get("defaultIndex"),this.log("info","get param columnName: "+this.columnName),this.log("info","get param cId: "+this.cId),this.currentIndex=this.defaultIndex}return n=l,l.prototype.log=function(l,e){this.logger[l](n.TAG+" "+e)},l.prototype.ionViewDidLoad=function(){this.loadNav()},l.prototype.refreshPage=function(){this.loadNav()},l.prototype.openColumn=function(l){var n=this,e={route:"list",params:{}};try{e=JSON.parse(l.jumpAddr.slice(1))}catch(n){this.log("warn",l.name+'配置的ionic路由格式(={"route":"","params":{}})不正确')}var t=e.route,u=e.params,i=void 0===u?{}:u;Object.assign(i,{cId:l.columnId,name:l.name}),this.routeUtils.go({navCtrl:this.navCtrl,route:t,params:i,successFn:function(e){n.loadChildColumnSuccess(l.name)},errorFn:function(e){n.loadChildColumnFailure(l.name)},preFn:function(){n.preLoadChildren()}})},l.prototype.openColumnItem=function(l){console.log(l),this.logger.debug(n.TAG+" openColumnSlidesItem() item: "+JSON.stringify(l)),"app"==l.actionType?"业委会"==l.name&&this.nativeJS.startActivity(p.a.ACTIVITY_START_MEETING):this.openColumn(l)},l.prototype.loadNav=function(){var l=this;if(-1===this.normalUserStatusLevels.indexOf(this.normalUser.getNormalUserStatus())){var n=this.pageTipProvider.instanceFatory(this.normalUser.user.checkStatus);return this.pageTipShow=n.show,void(this.pageTipData=n.data)}var e=this.normalUser.getAreaId();this.pageTipShow=this.pageTipProvider.loadingInstance().show,this.pageTipData=this.pageTipProvider.loadingInstance().data,this.aumsApiProvider.getColumnList({columnId:this.cId,areaId:e}).subscribe(function(n){if(l.log("debug","loadNav() 获取子栏目内容: "+JSON.stringify(n)),1==n.isSuccess&&n.data&&n.data.length)l.cloumnsSet=n.data,l.pageTipShow=l.pageTipProvider.successInstance().show,l.pageTipData=l.pageTipProvider.successInstance().data;else if(1==n.isSuccess)l.pageTipShow=l.pageTipProvider.emptyInstance().show,l.pageTipData=l.pageTipProvider.emptyInstance().data;else{var e=l.pageTipProvider.instanceFatory("error",n.message);l.pageTipShow=e.show,l.pageTipData=e.data}})},l.prototype.loadChildColumnSuccess=function(l){void 0===l&&(l=""),this.nativeJS.startActivity(p.a.ACTIVITY_HIDE_TAB),this.pageAuth.invalidUserRouter(l,this.navCtrl)},l.prototype.loadChildColumnFailure=function(l){this.utils.toast("加载"+l+"失败")},l.prototype.preLoadChildren=function(){},l.TAG="ColumnsPage",l;var n}(),v=e(193),b=e(937),I=function(){return function(){}}(),w=e(504),C=e(505),T=e(506),y=e(507),R=e(508),x=e(509),S=e(510),P=e(511),N=e(512),k=e(942),_=e(867),O=e(41),L=e(128),V=e(4),E=e(8),A=e(865),M=e(55),D=e(15),F=e(37),$=e(515),U=e(91),z=e(57),J=e(89),q=e(7),B=e(11),G=e(14),H=e(17),X=e(18),j=a["ɵcrt"]({encapsulation:2,styles:[],data:{}}),K=a["ɵccf"]("page-columns",h,function(l){return a["ɵvid"](0,[(l()(),a["ɵeld"](0,0,null,null,1,"page-columns",[],null,null,null,i,j)),a["ɵdid"](1,49152,null,0,h,[F.a,X.a,o.b,s.a,c.a,f.a,p.a,g.a,d.a,m.a],null,null)],null,null)},{normalUserStatusLevels:"normalUserStatusLevels"},{},[]),Y=e(31),Z=e(195),Q=e(63);e.d(n,"NavListPageModuleNgFactory",function(){return W});var W=a["ɵcmf"](I,[],function(l){return a["ɵmod"]([a["ɵmpd"](512,a.ComponentFactoryResolver,a["ɵCodegenComponentFactoryResolver"],[[8,[w.a,C.a,T.a,y.a,R.a,x.a,S.a,P.a,N.a,k.a,K]],[3,a.ComponentFactoryResolver],a.NgModuleRef]),a["ɵmpd"](4608,H.NgLocalization,H.NgLocaleLocalization,[a.LOCALE_ID,[2,H["ɵa"]]]),a["ɵmpd"](4608,Y.v,Y.v,[]),a["ɵmpd"](4608,Y.d,Y.d,[]),a["ɵmpd"](512,H.CommonModule,H.CommonModule,[]),a["ɵmpd"](512,Y.t,Y.t,[]),a["ɵmpd"](512,Y.j,Y.j,[]),a["ɵmpd"](512,Y.r,Y.r,[]),a["ɵmpd"](512,Z.a,Z.a,[]),a["ɵmpd"](512,Z.b,Z.b,[]),a["ɵmpd"](512,v.a,v.a,[]),a["ɵmpd"](512,b.a,b.a,[]),a["ɵmpd"](512,I,I,[]),a["ɵmpd"](256,Q.a,h,[])])})},865:function(l,n,e){"use strict";function t(l){return u["ɵvid"](0,[(l()(),u["ɵeld"](0,0,null,null,1,"div",[["class","toolbar-background"]],null,null,null,null,null)),u["ɵdid"](1,278528,null,0,i.NgClass,[u.IterableDiffers,u.KeyValueDiffers,u.ElementRef,u.Renderer2],{klass:[0,"klass"],ngClass:[1,"ngClass"]},null),(l()(),u["ɵeld"](2,0,null,null,8,"button",[["class","back-button"],["ion-button","bar-button"]],[[8,"hidden",0]],[[null,"click"]],function(l,n,e){var t=!0;if("click"===n){t=!1!==l.component.backButtonClick(e)&&t}return t},a.b,a.a)),u["ɵdid"](3,278528,null,0,i.NgClass,[u.IterableDiffers,u.KeyValueDiffers,u.ElementRef,u.Renderer2],{klass:[0,"klass"],ngClass:[1,"ngClass"]},null),u["ɵdid"](4,1097728,null,0,o.a,[[8,"bar-button"],r.a,u.ElementRef,u.Renderer],null,null),(l()(),u["ɵeld"](5,0,null,0,2,"ion-icon",[["class","back-button-icon"],["role","img"]],[[2,"hide",null]],null,null,null,null)),u["ɵdid"](6,278528,null,0,i.NgClass,[u.IterableDiffers,u.KeyValueDiffers,u.ElementRef,u.Renderer2],{klass:[0,"klass"],ngClass:[1,"ngClass"]},null),u["ɵdid"](7,147456,null,0,d.a,[r.a,u.ElementRef,u.Renderer],{name:[0,"name"]},null),(l()(),u["ɵeld"](8,0,null,0,2,"span",[["class","back-button-text"]],null,null,null,null,null)),u["ɵdid"](9,278528,null,0,i.NgClass,[u.IterableDiffers,u.KeyValueDiffers,u.ElementRef,u.Renderer2],{klass:[0,"klass"],ngClass:[1,"ngClass"]},null),(l()(),u["ɵted"](10,null,["",""])),u["ɵncd"](null,0),u["ɵncd"](null,1),u["ɵncd"](null,2),(l()(),u["ɵeld"](14,0,null,null,2,"div",[["class","toolbar-content"]],null,null,null,null,null)),u["ɵdid"](15,278528,null,0,i.NgClass,[u.IterableDiffers,u.KeyValueDiffers,u.ElementRef,u.Renderer2],{klass:[0,"klass"],ngClass:[1,"ngClass"]},null),u["ɵncd"](null,3)],function(l,n){var e=n.component;l(n,1,0,"toolbar-background","toolbar-background-"+e._mode);l(n,3,0,"back-button","back-button-"+e._mode);l(n,6,0,"back-button-icon","back-button-icon-"+e._mode);l(n,7,0,e._bbIcon);l(n,9,0,"back-button-text","back-button-text-"+e._mode);l(n,15,0,"toolbar-content","toolbar-content-"+e._mode)},function(l,n){var e=n.component;l(n,2,0,e._hideBb);l(n,5,0,u["ɵnov"](n,7)._hidden);l(n,10,0,e._backText)})}e.d(n,"a",function(){return s}),n.b=t;var u=e(1),i=e(17),a=e(46),o=e(32),r=e(4),d=e(62),s=(e(8),e(37),u["ɵcrt"]({encapsulation:2,styles:[],data:{}}))},867:function(l,n,e){"use strict";function t(l){return p["ɵvid"](0,[(l()(),p["ɵeld"](0,0,null,null,10,"ion-slide",[],null,null,null,g.b,g.a)),p["ɵdid"](1,180224,null,0,m.a,[p.ElementRef,p.Renderer,h.a],null,null),(l()(),p["ɵted"](-1,0,["\n          "])),(l()(),p["ɵeld"](3,0,null,0,6,"a",[["href","javascript:void 0"]],null,[[null,"tap"]],function(l,n,e){var t=!0;if("tap"===n){t=!1!==l.component.go(l.context.$implicit)&&t}return t},null,null)),(l()(),p["ɵted"](-1,null,["\n            "])),(l()(),p["ɵeld"](5,0,null,null,0,"img",[],[[8,"src",4]],null,null,null,null)),(l()(),p["ɵted"](-1,null,["\n   "])),(l()(),p["ɵeld"](7,0,null,null,1,"span",[],null,null,null,null,null)),(l()(),p["ɵted"](8,null,["\n  "," \n"])),(l()(),p["ɵted"](-1,null,["\n     "])),(l()(),p["ɵted"](-1,0,["\n        "]))],null,function(l,n){l(n,5,0,p["ɵinlineInterpolate"](1,"",n.context.$implicit.src,""));l(n,8,0,n.context.$implicit.name)})}function u(l){return p["ɵvid"](0,[(l()(),p["ɵeld"](0,0,null,null,8,null,null,null,null,null,null,null)),(l()(),p["ɵted"](-1,null,["\n      "])),(l()(),p["ɵeld"](2,0,null,null,5,"ion-slides",[["autoplay","3000"],["class","evm-slides"],["loop",""],["pager",""]],null,[[null,"ionSlideAutoplayStop"]],function(l,n,e){var t=!0;if("ionSlideAutoplayStop"===n){t=!1!==l.component.ionSlideAutoplayStop()&&t}return t},v.b,v.a)),p["ɵdid"](3,1228800,[[1,4]],0,h.a,[b.a,I.a,p.NgZone,[2,w.a],p.ElementRef,p.Renderer],{autoplay:[0,"autoplay"],loop:[1,"loop"],pager:[2,"pager"]},{ionSlideAutoplayStop:"ionSlideAutoplayStop"}),(l()(),p["ɵted"](-1,0,["\n        "])),(l()(),p["ɵand"](16777216,null,0,1,null,t)),p["ɵdid"](6,802816,null,0,f.NgForOf,[p.ViewContainerRef,p.TemplateRef,p.IterableDiffers],{ngForOf:[0,"ngForOf"]},null),(l()(),p["ɵted"](-1,0,["\n      "])),(l()(),p["ɵted"](-1,null,["\n    "]))],function(l,n){var e=n.component;l(n,3,0,"3000","","");l(n,6,0,e.list)},null)}function i(l){return p["ɵvid"](0,[p["ɵqud"](671088640,1,{slides:0}),(l()(),p["ɵted"](-1,null,["\n    "])),(l()(),p["ɵand"](16777216,null,null,1,null,u)),p["ɵdid"](3,16384,null,0,f.NgIf,[p.ViewContainerRef,p.TemplateRef],{ngIf:[0,"ngIf"]},null),(l()(),p["ɵted"](-1,null,["\n  "]))],function(l,n){var e=n.component;l(n,3,0,null==e.list?null:e.list.length)},null)}function a(l){return p["ɵvid"](0,[(l()(),p["ɵeld"](0,0,null,null,34,"div",[],null,null,null,null,null)),(l()(),p["ɵted"](-1,null,["\n            "])),(l()(),p["ɵeld"](2,0,null,null,31,"div",[["class","loading-effect"]],null,null,null,null,null)),(l()(),p["ɵted"](-1,null,["\n                "])),(l()(),p["ɵeld"](4,0,null,null,28,":svg:svg",[[":xml:space","preserve"],[":xmlns:xlink","http://www.w3.org/1999/xlink"],["height","30px"],["id","Layer_1"],["style","enable-background:new 0 0 50 50;"],["version","1.1"],["viewBox","0 0 24 30"],["width","24px"],["x","0px"],["xmlns","http://www.w3.org/2000/svg"],["y","0px"]],null,null,null,null,null)),(l()(),p["ɵted"](-1,null,["\n                    "])),(l()(),p["ɵeld"](6,0,null,null,7,":svg:rect",[["fill","#333"],["height","10"],["opacity","0.2"],["width","4"],["x","0"],["y","10"]],null,null,null,null,null)),(l()(),p["ɵted"](-1,null,["\n                        "])),(l()(),p["ɵeld"](8,0,null,null,0,":svg:animate",[["attributeName","opacity"],["attributeType","XML"],["begin","0s"],["dur","0.6s"],["repeatCount","indefinite"],["values","0.2; 1; .2"]],null,null,null,null,null)),(l()(),p["ɵted"](-1,null,["\n                        "])),(l()(),p["ɵeld"](10,0,null,null,0,":svg:animate",[["attributeName","height"],["attributeType","XML"],["begin","0s"],["dur","0.6s"],["repeatCount","indefinite"],["values","10; 20; 10"]],null,null,null,null,null)),(l()(),p["ɵted"](-1,null,["\n                        "])),(l()(),p["ɵeld"](12,0,null,null,0,":svg:animate",[["attributeName","y"],["attributeType","XML"],["begin","0s"],["dur","0.6s"],["repeatCount","indefinite"],["values","10; 5; 10"]],null,null,null,null,null)),(l()(),p["ɵted"](-1,null,["\n                    "])),(l()(),p["ɵted"](-1,null,["\n                    "])),(l()(),p["ɵeld"](15,0,null,null,7,":svg:rect",[["fill","#333"],["height","10"],["opacity","0.2"],["width","4"],["x","8"],["y","10"]],null,null,null,null,null)),(l()(),p["ɵted"](-1,null,["\n                        "])),(l()(),p["ɵeld"](17,0,null,null,0,":svg:animate",[["attributeName","opacity"],["attributeType","XML"],["begin","0.15s"],["dur","0.6s"],["repeatCount","indefinite"],["values","0.2; 1; .2"]],null,null,null,null,null)),(l()(),p["ɵted"](-1,null,["\n                        "])),(l()(),p["ɵeld"](19,0,null,null,0,":svg:animate",[["attributeName","height"],["attributeType","XML"],["begin","0.15s"],["dur","0.6s"],["repeatCount","indefinite"],["values","10; 20; 10"]],null,null,null,null,null)),(l()(),p["ɵted"](-1,null,["\n                        "])),(l()(),p["ɵeld"](21,0,null,null,0,":svg:animate",[["attributeName","y"],["attributeType","XML"],["begin","0.15s"],["dur","0.6s"],["repeatCount","indefinite"],["values","10; 5; 10"]],null,null,null,null,null)),(l()(),p["ɵted"](-1,null,["\n                    "])),(l()(),p["ɵted"](-1,null,["\n                    "])),(l()(),p["ɵeld"](24,0,null,null,7,":svg:rect",[["fill","#333"],["height","10"],["opacity","0.2"],["width","4"],["x","16"],["y","10"]],null,null,null,null,null)),(l()(),p["ɵted"](-1,null,["\n                        "])),(l()(),p["ɵeld"](26,0,null,null,0,":svg:animate",[["attributeName","opacity"],["attributeType","XML"],["begin","0.3s"],["dur","0.6s"],["repeatCount","indefinite"],["values","0.2; 1; .2"]],null,null,null,null,null)),(l()(),p["ɵted"](-1,null,["\n                        "])),(l()(),p["ɵeld"](28,0,null,null,0,":svg:animate",[["attributeName","height"],["attributeType","XML"],["begin","0.3s"],["dur","0.6s"],["repeatCount","indefinite"],["values","10; 20; 10"]],null,null,null,null,null)),(l()(),p["ɵted"](-1,null,["\n                        "])),(l()(),p["ɵeld"](30,0,null,null,0,":svg:animate",[["attributeName","y"],["attributeType","XML"],["begin","0.3s"],["dur","0.6s"],["repeatCount","indefinite"],["values","10; 5; 10"]],null,null,null,null,null)),(l()(),p["ɵted"](-1,null,["\n                    "])),(l()(),p["ɵted"](-1,null,["\n                "])),(l()(),p["ɵted"](-1,null,["\n            "])),(l()(),p["ɵted"](-1,null,["\n        "]))],null,null)}function o(l){return p["ɵvid"](0,[(l()(),p["ɵeld"](0,0,null,null,0,"div",[["class","iconfont icon-wuwangluo warn"]],[[4,"fontSize","px"]],null,null,null,null))],null,function(l,n){l(n,0,0,80)})}function r(l){return p["ɵvid"](0,[(l()(),p["ɵeld"](0,0,null,null,0,"div",[["class","iconfont icon-warn warn"]],[[4,"fontSize","px"]],null,null,null,null))],null,function(l,n){l(n,0,0,80)})}function d(l){return p["ɵvid"](0,[(l()(),p["ɵeld"](0,0,null,null,0,"div",[["class","iconfont icon-cuowu danger"]],[[4,"fontSize","px"]],null,null,null,null))],null,function(l,n){l(n,0,0,80)})}function s(l){return p["ɵvid"](0,[(l()(),p["ɵeld"](0,0,null,null,19,"div",[["class","evm-wrapper"]],null,[[null,"tap"]],function(l,n,e){var t=!0;if("tap"===n){t=!1!==l.component.refresh()&&t}return t},null,null)),(l()(),p["ɵted"](-1,null,["\n    "])),(l()(),p["ɵeld"](2,0,null,null,14,null,null,null,null,null,null,null)),p["ɵdid"](3,16384,null,0,f.NgSwitch,[],{ngSwitch:[0,"ngSwitch"]},null),(l()(),p["ɵted"](-1,null,["\n        "])),(l()(),p["ɵand"](16777216,null,null,1,null,a)),p["ɵdid"](6,278528,null,0,f.NgSwitchCase,[p.ViewContainerRef,p.TemplateRef,f.NgSwitch],{ngSwitchCase:[0,"ngSwitchCase"]},null),(l()(),p["ɵted"](-1,null,["\n        "])),(l()(),p["ɵand"](16777216,null,null,1,null,o)),p["ɵdid"](9,278528,null,0,f.NgSwitchCase,[p.ViewContainerRef,p.TemplateRef,f.NgSwitch],{ngSwitchCase:[0,"ngSwitchCase"]},null),(l()(),p["ɵted"](-1,null,["\n        "])),(l()(),p["ɵand"](16777216,null,null,1,null,r)),p["ɵdid"](12,278528,null,0,f.NgSwitchCase,[p.ViewContainerRef,p.TemplateRef,f.NgSwitch],{ngSwitchCase:[0,"ngSwitchCase"]},null),(l()(),p["ɵted"](-1,null,["\n        "])),(l()(),p["ɵand"](16777216,null,null,1,null,d)),p["ɵdid"](15,278528,null,0,f.NgSwitchCase,[p.ViewContainerRef,p.TemplateRef,f.NgSwitch],{ngSwitchCase:[0,"ngSwitchCase"]},null),(l()(),p["ɵted"](-1,null,["\n    "])),(l()(),p["ɵted"](-1,null,["\n    "])),(l()(),p["ɵeld"](18,0,null,null,0,"p",[],[[8,"innerHTML",1]],null,null,null,null)),(l()(),p["ɵted"](-1,null,["\n"]))],function(l,n){l(n,3,0,n.component.data.status);l(n,6,0,"loading");l(n,9,0,"offline");l(n,12,0,"timeout");l(n,15,0,"error")},function(l,n){l(n,18,0,n.component.msg)})}function c(l){return p["ɵvid"](0,[(l()(),p["ɵand"](16777216,null,null,1,null,s)),p["ɵdid"](1,16384,null,0,f.NgIf,[p.ViewContainerRef,p.TemplateRef],{ngIf:[0,"ngIf"]},null)],function(l,n){l(n,1,0,n.component.show)},null)}e.d(n,"a",function(){return C}),n.c=i,e.d(n,"b",function(){return T}),n.d=c;var p=e(1),f=(e(504),e(505),e(506),e(507),e(508),e(509),e(510),e(511),e(512),e(17)),g=e(869),m=e(197),h=e(126),v=e(870),b=e(4),I=e(7),w=e(8),C=(e(513),p["ɵcrt"]({encapsulation:0,styles:[""],data:{}})),T=p["ɵcrt"]({encapsulation:0,styles:["[_nghost-%COMP%]{display:-webkit-box;display:-ms-flexbox;display:flex;position:absolute;z-index:100;top:0;right:0;bottom:0;left:0}[_nghost-%COMP%]   .evm-wrapper[_ngcontent-%COMP%]{-webkit-box-flex:1;-ms-flex-positive:1;flex-grow:1;display:-webkit-box;display:-ms-flexbox;display:flex;-webkit-box-orient:vertical;-webkit-box-direction:normal;-ms-flex-direction:column;flex-direction:column;-webkit-box-pack:center;-ms-flex-pack:center;justify-content:center;-webkit-box-align:center;-ms-flex-align:center;align-items:center;color:#999}[_nghost-%COMP%]   .evm-wrapper[_ngcontent-%COMP%]   p[_ngcontent-%COMP%]{text-align:center;font-size:18px;margin-top:16px}[_nghost-%COMP%]   .loading-effect[_ngcontent-%COMP%]   svg[_ngcontent-%COMP%]   path[_ngcontent-%COMP%], [_nghost-%COMP%]   .loading-effect[_ngcontent-%COMP%]   svg[_ngcontent-%COMP%]   rect[_ngcontent-%COMP%]{fill:#6b9e54}[_nghost-%COMP%]   .evm-wrapper[_ngcontent-%COMP%]   .warn[_ngcontent-%COMP%]{color:#999}[_nghost-%COMP%]   .evm-wrapper[_ngcontent-%COMP%]   .danger[_ngcontent-%COMP%]{color:#f53d3d}"],data:{}})},869:function(l,n,e){"use strict";function t(l){return u["ɵvid"](2,[(l()(),u["ɵeld"](0,0,null,null,1,"div",[["class","slide-zoom"]],null,null,null,null,null)),u["ɵncd"](null,0)],null,null)}e.d(n,"a",function(){return i}),n.b=t;var u=e(1),i=u["ɵcrt"]({encapsulation:2,styles:[],data:{}})},870:function(l,n,e){"use strict";function t(l){return u["ɵvid"](2,[(l()(),u["ɵeld"](0,0,null,null,3,"div",[["class","swiper-container"]],[[1,"dir",0]],null,null,null,null)),(l()(),u["ɵeld"](1,0,null,null,1,"div",[["class","swiper-wrapper"]],null,null,null,null,null)),u["ɵncd"](null,0),(l()(),u["ɵeld"](3,0,null,null,0,"div",[["class","swiper-pagination"]],[[2,"hide",null]],null,null,null,null))],null,function(l,n){var e=n.component;l(n,0,0,e._rtl?"rtl":null);l(n,3,0,!e.pager)})}e.d(n,"a",function(){return i}),n.b=t;var u=e(1),i=(e(4),e(7),e(8),u["ɵcrt"]({encapsulation:2,styles:[],data:{}}))},873:function(l,n,e){"use strict";function t(l){return a["ɵvid"](0,[(l()(),a["ɵeld"](0,0,null,null,2,"div",[["class","infinite-loading-spinner"]],null,null,null,null,null)),(l()(),a["ɵeld"](1,0,null,null,1,"ion-spinner",[],[[2,"spinner-paused",null]],null,null,o.b,o.a)),a["ɵdid"](2,114688,null,0,r.a,[d.a,a.ElementRef,a.Renderer],{name:[0,"name"]},null)],function(l,n){l(n,2,0,n.component.loadingSpinner)},function(l,n){l(n,1,0,a["ɵnov"](n,2)._paused)})}function u(l){return a["ɵvid"](0,[(l()(),a["ɵeld"](0,0,null,null,0,"div",[["class","infinite-loading-text"]],[[8,"innerHTML",1]],null,null,null,null))],null,function(l,n){l(n,0,0,n.component.loadingText)})}function i(l){return a["ɵvid"](0,[(l()(),a["ɵeld"](0,0,null,null,4,"div",[["class","infinite-loading"]],null,null,null,null,null)),(l()(),a["ɵand"](16777216,null,null,1,null,t)),a["ɵdid"](2,16384,null,0,s.NgIf,[a.ViewContainerRef,a.TemplateRef],{ngIf:[0,"ngIf"]},null),(l()(),a["ɵand"](16777216,null,null,1,null,u)),a["ɵdid"](4,16384,null,0,s.NgIf,[a.ViewContainerRef,a.TemplateRef],{ngIf:[0,"ngIf"]},null)],function(l,n){var e=n.component;l(n,2,0,e.loadingSpinner);l(n,4,0,e.loadingText)},null)}e.d(n,"a",function(){return c}),n.b=i;var a=e(1),o=e(516),r=e(92),d=e(4),s=e(17),c=a["ɵcrt"]({encapsulation:2,styles:[],data:{}})},910:function(l,n,e){"use strict";e.d(n,"a",function(){return i});e(0);var t=e(1),u=(e(61),e(26),e(20),e(194),e(27),e(206),e(90),e(56),e(42)),i=function(){function l(l,n,e,u,i,a,o,r,d,s,c){this.utils=l,this.navCtrl=n,this.normalUser=e,this.zone=u,this.logger=i,this.pageTipProvider=a,this.homeProvider=o,this.aumsVoteApiProvider=r,this.aums=d,this.app=s,this.nativeJsExtend=c,this.isComponent=!1,this.onClickItem=new t.EventEmitter,this.pageIndex=1,this.isInfiniting=!1}return n=l,l.prototype.ngOnChanges=function(l){var n=["id","regionId"];for(var e in l)if(-1!==n.indexOf(e)&&this.inputPropChanged(l[e]))return this.load()},l.prototype.inputPropChanged=function(l){return l.currentValue!==l.previousValue},l.prototype.load=function(l){var n=this;this.list=null,this.pageTipShow=this.pageTipProvider.loadingInstance().show,this.pageTipData=this.pageTipProvider.loadingInstance().data,this.pageIndex=1,this.loadListTip="",this.infiniteScroll&&this.infiniteScroll.enable(!0);var e=this.normalUser.getAreaId();this.homeProvider.getRecommendList({id:this.id,areaId:this.regionId||e}).subscribe(function(e){Array.isArray(e)&&e.length?(n.pageTipShow=n.pageTipProvider.successInstance().show,n.pageTipData=n.pageTipProvider.successInstance().data,n.list=e,n.pageIndex++,l&&l("success")):(n.pageTipShow=n.pageTipProvider.emptyInstance().show,n.pageTipData=n.pageTipProvider.emptyInstance().data,l&&l("empty"))},function(e){var t=n.pageTipProvider.instanceFatory(e.status);n.pageTipShow=t.show,n.pageTipData=t.data,l&&l("error")})},l.prototype.doInfinite=function(l){var n=this;this.isInfiniting||(this.isInfiniting=!0,this.homeProvider.getRecommendList({id:this.id,areaId:this.regionId||this.normalUser.getAreaId(),curPage:this.pageIndex}).subscribe(function(e){Array.isArray(e)&&e.length?(n.list=n.list.concat(e),n.pageIndex++):(l.enable(!1),n.loadListTip="没有更多了")},function(l){},function(){l.complete(),n.isInfiniting=!1}))},l.prototype.imgLoadError=function(l,e){this.logger.error(n.TAG+" 加载图片失败(imageUrl=>"+l.target.src+")"),setTimeout(function(){e.loadThumbnailError=!0},0)},l.prototype.openItem=function(l){var n=this;"shortVideo"===l.type?this.updateHits(l.raw,function(){n.nativeJsExtend.startActivity(u.a.ACTIVITY_GO_TO_PLAY_VIDEO,l.raw.clientPlayUrl,l.raw.isHorizontal)}):this.onClickItem.emit(l.raw)},l.prototype.trackById=function(l,n){return n.id},l.prototype.refreshPage=function(){},l.prototype.share=function(l,n){l.stopPropagation();this.nativeJsExtend.startActivity(u.a.ACTIVITY_SHARE,"shortVideo"===n.type?this.app.appDownloadUrl:this.app.apache.apiEndPoint+"/"+this.app.apache.path+"/#/article/"+n.id)},l.prototype.updateHits=function(l,e){var t=this;this.app.loadingInstance=this.utils.presentLoading("加载中...");var u=this.utils.getUUID();this.aumsVoteApiProvider.castVote({columnId:l.id,userId:u||"pc",typeId:this.app.aums_vote.typeIds.shortVideoViewCount}).subscribe(function(l){t.logger.info(1==l.code?n.TAG+" updateHits() 更新点击次数成功":n.TAG+" updateHits() 更新点击次数失败"),t.app.closeLoading(),e&&e()})},l.TAG="RecommendListPage ",l;var n}()},937:function(l,n,e){"use strict";e.d(n,"a",function(){return t});e(0),e(61);var t=function(){return function(){}}()},942:function(l,n,e){"use strict";function t(l){return b["ɵvid"](0,[(l()(),b["ɵeld"](0,0,null,null,0,null,null,null,null,null,null,null))],null,null)}function u(l){return b["ɵvid"](0,[(l()(),b["ɵeld"](0,0,null,null,4,null,null,null,null,null,null,null)),(l()(),b["ɵted"](-1,null,["\n  "])),(l()(),b["ɵand"](16777216,null,null,1,null,t)),b["ɵdid"](3,540672,null,0,I.NgTemplateOutlet,[b.ViewContainerRef],{ngTemplateOutlet:[0,"ngTemplateOutlet"]},null),(l()(),b["ɵted"](-1,null,["\n"]))],function(l,n){l(n,3,0,b["ɵnov"](n.parent,7))},null)}function i(l){return b["ɵvid"](0,[(l()(),b["ɵeld"](0,0,null,null,0,null,null,null,null,null,null,null))],null,null)}function a(l){return b["ɵvid"](0,[(l()(),b["ɵted"](-1,null,["\n  "])),(l()(),b["ɵeld"](1,0,null,null,10,"ion-header",[],null,null,null,null,null)),b["ɵdid"](2,16384,null,0,w.a,[C.a,b.ElementRef,b.Renderer,[2,T.a]],null,null),(l()(),b["ɵted"](-1,null,["\n    "])),(l()(),b["ɵeld"](4,0,null,null,6,"ion-navbar",[["class","toolbar"]],[[8,"hidden",0],[2,"statusbar-padding",null]],null,null,y.b,y.a)),b["ɵdid"](5,49152,null,0,R.a,[x.a,[2,T.a],[2,S.a],C.a,b.ElementRef,b.Renderer],null,null),(l()(),b["ɵted"](-1,3,["\n      "])),(l()(),b["ɵeld"](7,0,null,3,2,"ion-title",[],null,null,null,P.b,P.a)),b["ɵdid"](8,49152,null,0,N.a,[C.a,b.ElementRef,b.Renderer,[2,k.a],[2,R.a]],null,null),(l()(),b["ɵted"](-1,0,["recommend-list"])),(l()(),b["ɵted"](-1,3,["\n    "])),(l()(),b["ɵted"](-1,null,["\n  "])),(l()(),b["ɵted"](-1,null,["\n  "])),(l()(),b["ɵeld"](13,0,null,null,5,"ion-content",[],[[2,"statusbar-padding",null],[2,"has-refresher",null],[2,"has-infinite-scroll",null]],null,null,_.b,_.a)),b["ɵdid"](14,4374528,null,0,O.a,[C.a,L.a,V.a,b.ElementRef,b.Renderer,x.a,E.a,b.NgZone,[2,T.a],[2,S.a]],null,null),(l()(),b["ɵted"](-1,1,["\n    "])),(l()(),b["ɵand"](16777216,null,1,1,null,i)),b["ɵdid"](17,540672,null,0,I.NgTemplateOutlet,[b.ViewContainerRef],{ngTemplateOutlet:[0,"ngTemplateOutlet"]},null),(l()(),b["ɵted"](-1,1,["\n  "])),(l()(),b["ɵted"](-1,null,["\n"]))],function(l,n){l(n,17,0,b["ɵnov"](n.parent,7))},function(l,n){l(n,4,0,b["ɵnov"](n,5)._hidden,b["ɵnov"](n,5)._sbPadding);l(n,13,0,b["ɵnov"](n,14).statusbarPadding,b["ɵnov"](n,14)._hasRefresher,b["ɵnov"](n,14)._hasInfiniteScroll)})}function o(l){return b["ɵvid"](0,[(l()(),b["ɵeld"](0,0,null,null,1,"page-tip",[],null,[[null,"refresh"]],function(l,n,e){var t=!0;if("refresh"===n){t=!1!==l.component.refreshPage()&&t}return t},A.d,A.b)),b["ɵdid"](1,311296,null,0,M.c,[D.c],{data:[0,"data"],show:[1,"show"]},{refreshPage:"refresh"})],function(l,n){var e=n.component;l(n,1,0,e.pageTipData,e.pageTipShow)},null)}function r(l){return b["ɵvid"](0,[(l()(),b["ɵeld"](0,0,null,null,1,"span",[],null,null,null,null,null)),(l()(),b["ɵted"](1,null,["",""]))],null,function(l,n){l(n,1,0,n.parent.parent.context.$implicit.time)})}function d(l){return b["ɵvid"](0,[(l()(),b["ɵeld"](0,0,null,null,23,null,null,null,null,null,null,null)),(l()(),b["ɵted"](-1,null,["\n          "])),(l()(),b["ɵeld"](2,0,null,null,20,"ion-item",[["class","video-item item item-block"]],[[2,"non-thumbnail",null]],[[null,"click"]],function(l,n,e){var t=!0;if("click"===n){t=!1!==l.component.openItem(l.parent.context.$implicit)&&t}return t},F.b,F.a)),b["ɵdid"](3,1097728,null,3,$.a,[U.a,C.a,b.ElementRef,b.Renderer,[2,z.a]],null,null),b["ɵqud"](335544320,3,{contentLabel:0}),b["ɵqud"](603979776,4,{_buttons:1}),b["ɵqud"](603979776,5,{_icons:1}),b["ɵdid"](7,16384,null,0,J.a,[],null,null),(l()(),b["ɵted"](-1,2,["\n            "])),(l()(),b["ɵeld"](9,0,null,2,1,"h2",[],null,null,null,null,null)),(l()(),b["ɵted"](10,null,["",""])),(l()(),b["ɵted"](-1,2,["\n            "])),(l()(),b["ɵeld"](12,0,null,2,3,"div",[["class","img-wrapper"]],null,null,null,null,null)),(l()(),b["ɵted"](-1,null,["\n              "])),(l()(),b["ɵeld"](14,0,null,null,0,"img",[],[[8,"src",4]],[[null,"error"]],function(l,n,e){var t=!0;if("error"===n){t=!1!==l.component.imgLoadError(e,l.parent.context.$implicit)&&t}return t},null,null)),(l()(),b["ɵted"](-1,null,["\n            "])),(l()(),b["ɵted"](-1,2,["\n            "])),(l()(),b["ɵeld"](17,0,null,2,4,"p",[],null,null,null,null,null)),(l()(),b["ɵted"](-1,null,["\n              "])),(l()(),b["ɵand"](16777216,null,null,1,null,r)),b["ɵdid"](20,16384,null,0,I.NgIf,[b.ViewContainerRef,b.TemplateRef],{ngIf:[0,"ngIf"]},null),(l()(),b["ɵted"](-1,null,["\n            "])),(l()(),b["ɵted"](-1,2,["\n          "])),(l()(),b["ɵted"](-1,null,["\n        "]))],function(l,n){l(n,20,0,n.parent.context.$implicit.time)},function(l,n){l(n,2,0,!n.parent.context.$implicit.thumbnail);l(n,10,0,n.parent.context.$implicit.title);l(n,14,0,n.parent.context.$implicit.thumbnail)})}function s(l){return b["ɵvid"](0,[(l()(),b["ɵeld"](0,0,null,null,4,"ion-thumbnail",[["item-left",""]],null,null,null,null,null)),b["ɵdid"](1,16384,null,0,q.a,[],null,null),(l()(),b["ɵted"](-1,null,["\n              "])),(l()(),b["ɵeld"](3,0,null,null,0,"img",[],[[8,"src",4]],[[null,"error"]],function(l,n,e){var t=!0;if("error"===n){t=!1!==l.component.imgLoadError(e,l.parent.parent.context.$implicit)&&t}return t},null,null)),(l()(),b["ɵted"](-1,null,["\n            "]))],null,function(l,n){l(n,3,0,n.parent.parent.context.$implicit.thumbnail)})}function c(l){return b["ɵvid"](0,[(l()(),b["ɵeld"](0,0,null,null,1,"span",[],null,null,null,null,null)),(l()(),b["ɵted"](1,null,["",""]))],null,function(l,n){l(n,1,0,n.parent.parent.context.$implicit.time)})}function p(l){return b["ɵvid"](0,[(l()(),b["ɵeld"](0,0,null,null,23,null,null,null,null,null,null,null)),(l()(),b["ɵted"](-1,null,["\n          "])),(l()(),b["ɵeld"](2,0,null,null,20,"ion-item",[["class","item item-block"]],[[2,"non-thumbnail",null]],[[null,"click"]],function(l,n,e){var t=!0;if("click"===n){t=!1!==l.component.openItem(l.parent.context.$implicit)&&t}return t},F.b,F.a)),b["ɵdid"](3,1097728,null,3,$.a,[U.a,C.a,b.ElementRef,b.Renderer,[2,z.a]],null,null),b["ɵqud"](335544320,6,{contentLabel:0}),b["ɵqud"](603979776,7,{_buttons:1}),b["ɵqud"](603979776,8,{_icons:1}),b["ɵdid"](7,16384,null,0,J.a,[],null,null),(l()(),b["ɵted"](-1,2,["\n            "])),(l()(),b["ɵand"](16777216,null,0,1,null,s)),b["ɵdid"](10,16384,null,0,I.NgIf,[b.ViewContainerRef,b.TemplateRef],{ngIf:[0,"ngIf"]},null),(l()(),b["ɵted"](-1,2,["\n            "])),(l()(),b["ɵeld"](12,0,null,2,1,"h2",[],null,null,null,null,null)),(l()(),b["ɵted"](13,null,["",""])),(l()(),b["ɵted"](-1,2,["\n            "])),(l()(),b["ɵeld"](15,0,null,2,6,"p",[],null,null,null,null,null)),(l()(),b["ɵted"](-1,null,["\n              "])),(l()(),b["ɵand"](16777216,null,null,1,null,c)),b["ɵdid"](18,16384,null,0,I.NgIf,[b.ViewContainerRef,b.TemplateRef],{ngIf:[0,"ngIf"]},null),(l()(),b["ɵted"](-1,null,["\n              "])),(l()(),b["ɵeld"](20,0,null,null,0,"span",[["class","iconfont icon-share"]],null,[[null,"click"]],function(l,n,e){var t=!0;if("click"===n){t=!1!==l.component.share(e,l.parent.context.$implicit)&&t}return t},null,null)),(l()(),b["ɵted"](-1,null,["\n            "])),(l()(),b["ɵted"](-1,2,["\n          "])),(l()(),b["ɵted"](-1,null,["\n        "]))],function(l,n){l(n,10,0,n.parent.context.$implicit.thumbnail);l(n,18,0,n.parent.context.$implicit.time)},function(l,n){l(n,2,0,!n.parent.context.$implicit.thumbnail);l(n,13,0,n.parent.context.$implicit.title)})}function f(l){return b["ɵvid"](0,[(l()(),b["ɵeld"](0,0,null,null,11,null,null,null,null,null,null,null)),(l()(),b["ɵted"](-1,null,["\n      "])),(l()(),b["ɵeld"](2,0,null,null,8,null,null,null,null,null,null,null)),b["ɵdid"](3,16384,null,0,I.NgSwitch,[],{ngSwitch:[0,"ngSwitch"]},null),(l()(),b["ɵted"](-1,null,["\n        "])),(l()(),b["ɵand"](16777216,null,null,1,null,d)),b["ɵdid"](6,278528,null,0,I.NgSwitchCase,[b.ViewContainerRef,b.TemplateRef,I.NgSwitch],{ngSwitchCase:[0,"ngSwitchCase"]},null),(l()(),b["ɵted"](-1,null,["\n        "])),(l()(),b["ɵand"](16777216,null,null,1,null,p)),b["ɵdid"](9,16384,null,0,I.NgSwitchDefault,[b.ViewContainerRef,b.TemplateRef,I.NgSwitch],null,null),(l()(),b["ɵted"](-1,null,["\n      "])),(l()(),b["ɵted"](-1,null,["\n    "]))],function(l,n){l(n,3,0,n.context.$implicit.raw.fclassName);l(n,6,0,"视频")},null)}function g(l){return b["ɵvid"](0,[(l()(),b["ɵeld"](0,0,[[1,0],["recommendList",1]],null,5,"ion-list",[],null,null,null,null,null)),b["ɵdid"](1,16384,null,0,B.a,[C.a,b.ElementRef,b.Renderer,L.a,G.l,V.a],null,null),(l()(),b["ɵted"](-1,null,["\n    "])),(l()(),b["ɵand"](16777216,null,null,1,null,f)),b["ɵdid"](4,802816,null,0,I.NgForOf,[b.ViewContainerRef,b.TemplateRef,b.IterableDiffers],{ngForOf:[0,"ngForOf"],ngForTrackBy:[1,"ngForTrackBy"]},null),(l()(),b["ɵted"](-1,null,["\n  "]))],function(l,n){var e=n.component;l(n,4,0,e.list,e.trackById)},null)}function m(l){return b["ɵvid"](0,[(l()(),b["ɵeld"](0,0,null,null,11,null,null,null,null,null,null,null)),(l()(),b["ɵted"](-1,null,["\n    "])),(l()(),b["ɵeld"](2,0,null,null,1,"div",[["class","load-list-tip"]],null,null,null,null,null)),(l()(),b["ɵted"](3,null,["",""])),(l()(),b["ɵted"](-1,null,["\n    "])),(l()(),b["ɵeld"](5,0,null,null,5,"ion-infinite-scroll",[],null,[[null,"ionInfinite"]],function(l,n,e){var t=!0;if("ionInfinite"===n){t=!1!==l.component.doInfinite(e)&&t}return t},null,null)),b["ɵdid"](6,1196032,[[2,4]],0,H.a,[O.a,b.NgZone,b.ElementRef,V.a],null,{ionInfinite:"ionInfinite"}),(l()(),b["ɵted"](-1,null,["\n      "])),(l()(),b["ɵeld"](8,0,null,null,1,"ion-infinite-scroll-content",[],[[1,"state",0]],null,null,X.b,X.a)),b["ɵdid"](9,114688,null,0,j.a,[H.a,C.a],null,null),(l()(),b["ɵted"](-1,null,["\n    "])),(l()(),b["ɵted"](-1,null,["\n  "]))],function(l,n){l(n,9,0)},function(l,n){l(n,3,0,n.component.loadListTip);l(n,8,0,b["ɵnov"](n,9).inf.state)})}function h(l){return b["ɵvid"](0,[(l()(),b["ɵted"](-1,null,["\n  "])),(l()(),b["ɵand"](16777216,null,null,1,null,o)),b["ɵdid"](2,16384,null,0,I.NgIf,[b.ViewContainerRef,b.TemplateRef],{ngIf:[0,"ngIf"]},null),(l()(),b["ɵted"](-1,null,["\n  "])),(l()(),b["ɵand"](16777216,null,null,1,null,g)),b["ɵdid"](5,16384,null,0,I.NgIf,[b.ViewContainerRef,b.TemplateRef],{ngIf:[0,"ngIf"]},null),(l()(),b["ɵted"](-1,null,["\n  "])),(l()(),b["ɵand"](16777216,null,null,1,null,m)),b["ɵdid"](8,16384,null,0,I.NgIf,[b.ViewContainerRef,b.TemplateRef],{ngIf:[0,"ngIf"]},null),(l()(),b["ɵted"](-1,null,["\n"]))],function(l,n){var e=n.component;l(n,2,0,e.pageTipShow);l(n,5,0,!e.pageTipShow&&(null==e.list?null:e.list.length));l(n,8,0,null==e.list?null:e.list.length)},null)}function v(l){return b["ɵvid"](0,[b["ɵqud"](671088640,1,{recommendList:0}),b["ɵqud"](671088640,2,{infiniteScroll:0}),(l()(),b["ɵand"](16777216,null,null,1,null,u)),b["ɵdid"](3,16384,null,0,I.NgIf,[b.ViewContainerRef,b.TemplateRef],{ngIf:[0,"ngIf"],ngIfElse:[1,"ngIfElse"]},null),(l()(),b["ɵted"](-1,null,["\n"])),(l()(),b["ɵand"](0,[["elseBlock",2]],null,0,null,a)),(l()(),b["ɵted"](-1,null,["\n"])),(l()(),b["ɵand"](0,[["recommendListTpl",2]],null,0,null,h)),(l()(),b["ɵted"](-1,null,["\n"]))],function(l,n){l(n,3,0,n.component.isComponent,b["ɵnov"](n,5))},null)}e.d(n,"b",function(){return il}),n.c=v,e.d(n,"a",function(){return al});var b=e(1),I=e(17),w=e(128),C=e(4),T=e(8),y=e(865),R=e(55),x=e(15),S=e(37),P=e(515),N=e(91),k=e(57),_=e(514),O=e(38),L=e(7),V=e(14),E=e(47),A=e(867),M=e(193),D=e(41),F=e(513),$=e(36),U=e(33),z=e(67),J=e(127),q=e(199),B=e(89),G=e(11),H=e(129),X=e(873),j=e(198),K=e(910),Y=e(26),Z=e(56),Q=e(23),W=e(194),ll=e(206),nl=e(90),el=e(27),tl=e(20),ul=e(42),il=b["ɵcrt"]({encapsulation:2,styles:[],data:{}}),al=b["ɵccf"]("page-recommend-list",K.a,function(l){return b["ɵvid"](0,[(l()(),b["ɵeld"](0,0,null,null,1,"page-recommend-list",[],null,null,null,v,il)),b["ɵdid"](1,573440,null,0,K.a,[Y.a,S.a,Z.a,b.NgZone,Q.b,W.a,ll.a,nl.a,el.a,tl.a,ul.a],null,null)],null,null)},{isComponent:"isComponent",id:"id",regionId:"regionId"},{onClickItem:"onClickItem"},[])}});