(window.webpackJsonp=window.webpackJsonp||[]).push([[5],{NhWQ:function(n,l,e){"use strict";e.d(l,"a",function(){return t});var t=function(){return function(){}}()},aTrf:function(n,l,e){"use strict";e.d(l,"a",function(){return a}),e("atIC"),e("h1wG"),e("lbwW");var t=e("F/XL"),u=e("67Y/"),i=e("wd/R"),a=function(){function n(n,l,e){this.app=n,this.normalUserService=l,this.aumsService=e}return n.prototype.getList=function(n){var l=n.page,e={columnId:n.columnId,curPage:void 0===l?1:l},u=this.normalUserService.user;return u?(Object.assign(e,{areaId:u.areaId,publicly:"游客"===u.role?1:null}),this.aumsService.getArticleList(e)):Object(t.a)({status:"error",message:"获取普通用户信息失败"})},n.prototype.getLatestTenShortVideoList=function(){var n=this.normalUserService.user;return n?this.aumsService.getViewableShortVideoList({userId:n.userId,userType:n.userType,areaId:n.areaId}).pipe(Object(u.a)(function(n){return"success"===n.status&&(n.data=n.data.map(function(n){return{id:n.id,title:n.name,type:"video",thumbnail:n.imageUrl,playUrl:n.clientPlayUrl,author:n.uploadUserName||"",time:i(n.uploadTime),publishTime:i(n.uploadTime).isValid?i(n.uploadTime):null,isHorizontal:n.isHorizontal,raw:n}})),n})):Object(t.a)({status:"error",message:"获取普通用户信息失败"})},n}()},"bI+B":function(n,l,e){"use strict";e.d(l,"a",function(){return o});var t=e("CcnG"),u=(e("aTrf"),e("atIC"),e("lbwW"),e("0Huk"),e("1FC6")),i=e("nIpe"),a=e("F/XL"),o=(e("h1wG"),e("+SBZ"),e("B0rH"),function(){function n(n,l,e,u,i,a,o,r,c,s,p){this.articleListService=n,this.app=l,this.logger=e,this.router=u,this.elementRef=i,this.aums=a,this.statsService=o,this.normalUser=r,this.umsUser=c,this.utils=s,this.activatedRoute=p,this.isGather=!1,this.pageIndex=1,this.isLoading=!1,this.isInit=!1,this.messageChannel=new t.EventEmitter,this.isError=!1,this.skeletonNumber=Array(7).fill(7),this.jiaLingArticleListUI=this.app.getFeatureConfig("jiaLingArticleListUI"),this.isShowVote=!1,this.isShowShare=!1}return n.prototype.ngOnInit=function(){var n=this;this.appChannelSubscription=this.app.appChannel.subscribe(function(l){if(l.type===u.a.UPDATELISTSTATS){var e=l.data;if(e&&"article"===e.type&&"view"===e.statsType)Array.isArray(n.list)&&(t=n.list.find(function(n){return n.id==e.id}))&&(t.viewCount=e.count);else if(e&&"article"===e.type&&"like"===e.statsType){var t;Array.isArray(n.list)&&(t=n.list.find(function(n){return n.id==e.id}))&&(t.favorCount=e.count,t.isFavor=!t.isFavor)}}}),this.jiaLingArticleListUI&&"JiaLingHomePage"===this.activatedRoute.component.TAG?this.isShowShare=!0:this.jiaLingArticleListUI&&(this.isShowVote=!0)},n.prototype.ngOnDestroy=function(){this.appChannelSubscription.unsubscribe()},n.prototype.ngOnChanges=function(n){n.isGather&&!0===n.isGather.currentValue?this.init():n.columnId&&n.columnId.currentValue!==n.columnId.previousValue&&this.init()},n.prototype.init=function(){this.isInit&&!this.isError||(this.pageIndex=1,this.list=[],this.isInit=!1,this.isError=!1,this.pageAction="init",this.isLoading=!1,this.pageTipPayload={status:"loading"},this.showSkeleton=!0,this.getList())},n.prototype.refresh=function(){this.pageIndex=1,this.list=[],this.isInit=!1,this.isError=!1,this.pageAction="refresh",this.isLoading=!1,this.pageTipPayload={status:"loading"},this.showSkeleton=!0,this.getList()},n.prototype.loadmore=function(){this.pageAction="more",this.getList()},n.prototype.fromNow=function(n){return n?n.locale("zh-cn").fromNow():""},n.prototype.openItem=function(n){this.aums.openArticle(n),"video"===n.type&&this.addVideoPlayCount(n)},n.prototype.getList=function(){var l=this;this.isLoading||(this.isLoading=!0,this.getListSubscription&&(this.getListSubscription.unsubscribe(),this.getListSubscription=null),this.getListSubscription=this.articleListService.getList({columnId:this.columnId,page:this.pageIndex}).pipe(Object(i.a)(this.isGather&&1===this.pageIndex?this.articleListService.getLatestTenShortVideoList():Object(a.a)(null))).subscribe(function(e){var t,u=e[0],i=e[1];switch(l.isLoading=!1,u.status){case"success":var a;a=u.data.filter(l.jiaLingArticleListUI?function(n){return 1===n.checkStatus&&(void 0===n.raw.checkFlag||1===n.raw.checkFlag)}:function(n){return 1==+n.raw.fchecked||1===n.raw.checkStatus});var o=void 0;o=1===l.pageIndex?a.slice():l.list.concat(a),null!=i&&i.data&&(i.data=i.data.filter(function(n){return 1===n.raw.checkStatus}),(o=o.concat(i.data)).sort(function(n,l){return l.time-n.time})),l.logger.info(n.TAG+" list: "+JSON.stringify(o)),l.list=o,u.data.length<l.app.appConfig.pageSize?u.status="empty":l.pageIndex++,t="success";var r=u.data.reduce(function(n,l){var e=l.id||0;return""===n?e:n+","+e},"");l.statsService.fetchTotalCount({ids:r,favorTypeId:l.app.appConfig.vote.type.favor.article.typeId,viewTypeId:l.app.appConfig.vote.type.view.article.typeId,userId:l.normalUser.user.userId}).subscribe(function(e){l.logger.debug(n.TAG+" fetchTotalCount() res: "+JSON.stringify(e)),e&&a.forEach(function(n){var l=e.find(function(l){return l.id===n.id.toString()});l&&(n.isFavor=l.isFavor,n.favorCount=+l.favorCount,n.viewCount=+l.viewCount)})});break;case"empty":t="empty";break;default:l.isError=!0,t=u.status}"init"!==l.pageAction&&"refresh"!==l.pageAction||(l.isInit=!0,l.showSkeleton=!1,l.pageTipPayload={status:t,message:u.message}),l.messageChannel.emit({type:l.pageAction,status:u.status}),l.pageAction="complete"}))},n.prototype.toggleLike=function(l,e){var t=this;l.stopPropagation(),this.normalUser.user.userId?this.aums.castVote(e.id,this.app.appConfig.vote.type.favor.article.typeId).subscribe(function(l){t.logger.info(n.TAG+" toggleLike() payload: "+JSON.stringify(l)),"success"===l.status?1===l.data.state?(e.isFavor=!0,e.favorCount++):(e.isFavor=!1,e.favorCount--):t.utils.toast("fail","点赞失败，请稍后重试！")}):this.umsUser.confirmLogin("未登录不能点赞","点击确定 跳转登录")},n.prototype.addVideoPlayCount=function(n){this.aums.castVote(n.id,this.app.appConfig.vote.type.view.article.typeId).subscribe(function(l){"success"===l.status&&n.viewCount++})},n.prototype.shareArticle=function(n,l){n.stopPropagation(),this.app.share("article",{title:l.title,desc:l.raw.fsummary,thumbnail:l.thumbnail,articleId:l.id,columnId:l.raw.columnId})},n.TAG="ArticleListComponent",n}())},oOVD:function(n,l,e){"use strict";var t=e("CcnG"),u=e("Ip0R"),i=e("MKJQ"),a=e("jy/b"),o=e("prAe"),r=e("PqVL"),c=e("57in"),s=e("xvo6"),p=e("bI+B"),d=e("aTrf"),f=e("atIC"),m=e("U3FM"),g=e("ZYCi"),h=e("lbwW"),v=e("0Huk"),b=e("h1wG"),C=e("+SBZ"),I=e("B0rH");e.d(l,"b",function(){return w}),e.d(l,"c",function(){return Q}),e.d(l,"a",function(){return q});var w=t["ɵcrt"]({encapsulation:0,styles:[['[_nghost-%COMP%]{position:relative;width:100%}  .am-card{min-height:auto}  .am-card::before{display:none!important}  .am-card::after{background-color:#e6e6e6!important;height:2px!important}.item-sub[_ngcontent-%COMP%]{font-size:var(--font-size-sm);color:var(--text-color-secondary);display:flex;flex-flow:row nowrap;justify-content:space-between;align-items:flex-end}.item-sub[_ngcontent-%COMP%]   span[_ngcontent-%COMP%]:not(:first-child){margin-left:8px}.item-sub[_ngcontent-%COMP%]   span[_ngcontent-%COMP%]   ion-icon[_ngcontent-%COMP%], .item-sub[_ngcontent-%COMP%]   span[_ngcontent-%COMP%]   ion-label[_ngcontent-%COMP%]{display:inline-block;vertical-align:middle}.item-sub[_ngcontent-%COMP%]   span[_ngcontent-%COMP%]   ion-icon[_ngcontent-%COMP%]{font-size:var(--font-size-lg);padding-right:2px}.item-sub[_ngcontent-%COMP%]   .footer-shareBtn-wrap[_ngcontent-%COMP%]   ion-icon[_ngcontent-%COMP%]{color:var(--ion-color-primary)}.video-item[_ngcontent-%COMP%]   .thumbnail[_ngcontent-%COMP%]{position:relative;height:180px;overflow:hidden;background-size:100% auto;background-position:center center;border-radius:5px}.video-item[_ngcontent-%COMP%]   .thumbnail[_ngcontent-%COMP%]   .player-icon[_ngcontent-%COMP%]{position:absolute;top:50%;left:50%;transform:translate(-50%,-50%);z-index:990}.video-item[_ngcontent-%COMP%]   .thumbnail[_ngcontent-%COMP%]   .player-icon[_ngcontent-%COMP%]:before{width:50px;height:50px;background-position:0 0;background-image:url(player-bg.31ab007ab42a1f30c2dd.png);background-size:100px 150px;display:inline-block;background-repeat:no-repeat;content:""}.thumbnail-box[_ngcontent-%COMP%]{display:flex;padding:16px 0}.thumbnail-box[_ngcontent-%COMP%]   .right[_ngcontent-%COMP%]{flex:6;height:100%;display:flex;flex-direction:column;justify-content:space-between}.thumbnail-box[_ngcontent-%COMP%]   .right[_ngcontent-%COMP%]   .item-title[_ngcontent-%COMP%]{text-overflow:ellipsis;display:-webkit-box;-webkit-line-clamp:2;overflow:hidden;-webkit-box-orient:vertical}.thumbnail-box[_ngcontent-%COMP%]   .thumbnail[_ngcontent-%COMP%]{flex:4;max-height:130px;overflow:hidden;margin-right:10px;border-radius:5px}.thumbnail-box[_ngcontent-%COMP%]   .thumbnail[_ngcontent-%COMP%]   img[_ngcontent-%COMP%]{width:100%;height:100%}.skeleton[_ngcontent-%COMP%]   ion-skeleton-text[_ngcontent-%COMP%]{height:3vh}.skeleton[_ngcontent-%COMP%]   ion-skeleton-text[_ngcontent-%COMP%] ~ ion-skeleton-text[_ngcontent-%COMP%]{margin-top:10px}']],data:{}});function R(n){return t["ɵvid"](0,[(n()(),t["ɵeld"](0,0,null,null,1,"div",[["class","thumbnail"]],[[4,"backgroundImage",null]],null,null,null,null)),(n()(),t["ɵeld"](1,0,null,null,0,"div",[["class","player-icon"]],null,null,null,null,null))],null,function(n,l){n(l,0,0,"url("+(null==l.parent.parent.context.$implicit?null:l.parent.parent.context.$implicit.thumbnail)+")")})}function x(n){return t["ɵvid"](0,[(n()(),t["ɵeld"](0,0,null,null,1,"span",[],null,null,null,null,null)),(n()(),t["ɵted"](1,null,["","阅读"]))],null,function(n,l){n(l,1,0,l.parent.parent.parent.context.$implicit.viewCount||0)})}function y(n){return t["ɵvid"](0,[(n()(),t["ɵeld"](0,0,null,null,7,"div",[["class","item-sub"]],null,null,null,null,null)),(n()(),t["ɵeld"](1,0,null,null,6,"div",[],null,null,null,null,null)),(n()(),t["ɵeld"](2,0,null,null,1,"span",[],null,null,null,null,null)),(n()(),t["ɵted"](3,null,["",""])),(n()(),t["ɵand"](16777216,null,null,1,null,x)),t["ɵdid"](5,16384,null,0,u.NgIf,[t.ViewContainerRef,t.TemplateRef],{ngIf:[0,"ngIf"]},null),(n()(),t["ɵeld"](6,0,null,null,1,"span",[],null,null,null,null,null)),(n()(),t["ɵted"](7,null,["",""]))],function(n,l){n(l,5,0,l.parent.parent.context.$implicit.viewCount)},function(n,l){var e=l.component;n(l,3,0,l.parent.parent.context.$implicit.author),n(l,7,0,e.fromNow(l.parent.parent.context.$implicit.publishTime))})}function S(n){return t["ɵvid"](0,[(n()(),t["ɵeld"](0,0,null,null,3,"div",[["class","footer-shareBtn-wrap"]],null,[[null,"click"]],function(n,l,e){var t=!0;return"click"===l&&(t=!1!==n.component.shareArticle(e,n.parent.parent.parent.context.$implicit)&&t),t},null,null)),(n()(),t["ɵeld"](1,0,null,null,2,"span",[],null,null,null,null,null)),(n()(),t["ɵeld"](2,0,null,null,1,"ion-icon",[["name","arrow-redo-outline"]],null,null,null,i.vb,i.u)),t["ɵdid"](3,49152,null,0,a.D,[t.ChangeDetectorRef,t.ElementRef,t.NgZone],{name:[0,"name"]},null)],function(n,l){n(l,3,0,"arrow-redo-outline")},null)}function N(n){return t["ɵvid"](0,[(n()(),t["ɵeld"](0,0,null,null,1,"ion-icon",[["src","assets/icons/comment/icon-thumbs.svg"],["style","font-size: 19px"]],null,null,null,i.vb,i.u)),t["ɵdid"](1,49152,null,0,a.D,[t.ChangeDetectorRef,t.ElementRef,t.NgZone],{src:[0,"src"]},null)],function(n,l){n(l,1,0,"assets/icons/comment/icon-thumbs.svg")},null)}function O(n){return t["ɵvid"](0,[(n()(),t["ɵeld"](0,0,null,null,1,"ion-icon",[["src","assets/icons/comment/icon-thumbs-up.svg"],["style","font-size: 19px"]],null,null,null,i.vb,i.u)),t["ɵdid"](1,49152,null,0,a.D,[t.ChangeDetectorRef,t.ElementRef,t.NgZone],{src:[0,"src"]},null)],function(n,l){n(l,1,0,"assets/icons/comment/icon-thumbs-up.svg")},null)}function L(n){return t["ɵvid"](0,[(n()(),t["ɵeld"](0,0,null,null,14,"div",[["class","footer-voteIcon-wrap"]],null,null,null,null,null)),(n()(),t["ɵeld"](1,0,null,null,5,"span",[],null,null,null,null,null)),(n()(),t["ɵeld"](2,0,null,null,1,"ion-icon",[["name","eye-outline"]],null,null,null,i.vb,i.u)),t["ɵdid"](3,49152,null,0,a.D,[t.ChangeDetectorRef,t.ElementRef,t.NgZone],{name:[0,"name"]},null),(n()(),t["ɵeld"](4,0,null,null,2,"ion-label",[],null,null,null,i.Eb,i.D)),t["ɵdid"](5,49152,null,0,a.O,[t.ChangeDetectorRef,t.ElementRef,t.NgZone],null,null),(n()(),t["ɵted"](6,0,["",""])),(n()(),t["ɵeld"](7,0,null,null,7,"span",[],null,[[null,"click"]],function(n,l,e){var t=!0;return"click"===l&&(t=!1!==n.component.toggleLike(e,n.parent.parent.parent.context.$implicit)&&t),t},null,null)),(n()(),t["ɵand"](16777216,null,null,1,null,N)),t["ɵdid"](9,16384,null,0,u.NgIf,[t.ViewContainerRef,t.TemplateRef],{ngIf:[0,"ngIf"]},null),(n()(),t["ɵand"](16777216,null,null,1,null,O)),t["ɵdid"](11,16384,null,0,u.NgIf,[t.ViewContainerRef,t.TemplateRef],{ngIf:[0,"ngIf"]},null),(n()(),t["ɵeld"](12,0,null,null,2,"ion-label",[],null,null,null,i.Eb,i.D)),t["ɵdid"](13,49152,null,0,a.O,[t.ChangeDetectorRef,t.ElementRef,t.NgZone],null,null),(n()(),t["ɵted"](14,0,["",""]))],function(n,l){n(l,3,0,"eye-outline"),n(l,9,0,!l.parent.parent.parent.context.$implicit.isFavor),n(l,11,0,l.parent.parent.parent.context.$implicit.isFavor)},function(n,l){n(l,6,0,l.parent.parent.parent.context.$implicit.viewCount||0),n(l,14,0,l.parent.parent.parent.context.$implicit.favorCount||0)})}function T(n){return t["ɵvid"](0,[(n()(),t["ɵeld"](0,0,null,null,8,"div",[["class","item-sub"]],null,null,null,null,null)),(n()(),t["ɵeld"](1,0,null,null,3,"div",[],null,null,null,null,null)),(n()(),t["ɵeld"](2,0,null,null,2,"span",[],null,null,null,null,null)),(n()(),t["ɵted"](3,null,[" "," "])),t["ɵppd"](4,2),(n()(),t["ɵand"](16777216,null,null,1,null,S)),t["ɵdid"](6,16384,null,0,u.NgIf,[t.ViewContainerRef,t.TemplateRef],{ngIf:[0,"ngIf"]},null),(n()(),t["ɵand"](16777216,null,null,1,null,L)),t["ɵdid"](8,16384,null,0,u.NgIf,[t.ViewContainerRef,t.TemplateRef],{ngIf:[0,"ngIf"]},null)],function(n,l){var e=l.component;n(l,6,0,e.isShowShare),n(l,8,0,e.isShowVote)},function(n,l){var e=t["ɵunv"](l,3,0,n(l,4,0,t["ɵnov"](l.parent.parent.parent,0),l.parent.parent.context.$implicit.publishTime,"yyyy-MM-dd"))||"时间未知";n(l,3,0,e)})}function k(n){return t["ɵvid"](0,[(n()(),t["ɵeld"](0,0,null,null,16,null,null,null,null,null,null,null)),(n()(),t["ɵeld"](1,0,null,null,15,"div",[["class","card-item video-item"]],null,null,null,null,null)),(n()(),t["ɵeld"](2,0,null,null,5,"ion-card-header",[],null,null,null,i.ib,i.i)),t["ɵdid"](3,49152,null,0,a.p,[t.ChangeDetectorRef,t.ElementRef,t.NgZone],null,null),(n()(),t["ɵeld"](4,0,null,0,3,"ion-card-title",[],null,null,null,i.kb,i.k)),t["ɵdid"](5,49152,null,0,a.r,[t.ChangeDetectorRef,t.ElementRef,t.NgZone],null,null),(n()(),t["ɵeld"](6,0,null,0,1,"p",[["class","item-title"]],null,null,null,null,null)),(n()(),t["ɵted"](7,null,[" "," "])),(n()(),t["ɵeld"](8,0,null,null,8,"ion-card-content",[],null,null,null,i.hb,i.h)),t["ɵdid"](9,49152,null,0,a.o,[t.ChangeDetectorRef,t.ElementRef,t.NgZone],null,null),(n()(),t["ɵand"](16777216,null,0,1,null,R)),t["ɵdid"](11,16384,null,0,u.NgIf,[t.ViewContainerRef,t.TemplateRef],{ngIf:[0,"ngIf"]},null),(n()(),t["ɵeld"](12,0,null,0,1,"WhiteSpace",[],[[2,"am-whitespace",null],[2,"am-whitespace-xs",null],[2,"am-whitespace-sm",null],[2,"am-whitespace-md",null],[2,"am-whitespace-lg",null],[2,"am-whitespace-xl",null]],null,null,o.u,o.m)),t["ɵdid"](13,49152,null,0,r.Jc,[],{size:[0,"size"]},null),(n()(),t["ɵand"](16777216,null,0,1,null,y)),t["ɵdid"](15,16384,null,0,u.NgIf,[t.ViewContainerRef,t.TemplateRef],{ngIf:[0,"ngIf"],ngIfElse:[1,"ngIfElse"]},null),(n()(),t["ɵand"](0,[["jiaLingUI",2]],0,0,null,T))],function(n,l){var e=l.component;n(l,11,0,l.parent.context.$implicit.thumbnail),n(l,13,0,"xl"),n(l,15,0,!e.jiaLingArticleListUI,t["ɵnov"](l,16))},function(n,l){n(l,7,0,l.parent.context.$implicit.title),n(l,12,0,t["ɵnov"](l,13).amWhiteSpace,t["ɵnov"](l,13).amWhitespaceXs,t["ɵnov"](l,13).amWhitespaceSm,t["ɵnov"](l,13).amWhitespaceMd,t["ɵnov"](l,13).amWhitespaceLg,t["ɵnov"](l,13).amWhitespaceXl)})}function P(n){return t["ɵvid"](0,[(n()(),t["ɵeld"](0,0,null,null,1,"span",[],null,null,null,null,null)),(n()(),t["ɵted"](1,null,["","阅读"]))],null,function(n,l){n(l,1,0,l.parent.parent.parent.parent.context.$implicit.viewCount||0)})}function D(n){return t["ɵvid"](0,[(n()(),t["ɵeld"](0,0,null,null,7,"div",[["class","item-sub"]],null,null,null,null,null)),(n()(),t["ɵeld"](1,0,null,null,6,"div",[],null,null,null,null,null)),(n()(),t["ɵeld"](2,0,null,null,1,"span",[],null,null,null,null,null)),(n()(),t["ɵted"](3,null,["",""])),(n()(),t["ɵand"](16777216,null,null,1,null,P)),t["ɵdid"](5,16384,null,0,u.NgIf,[t.ViewContainerRef,t.TemplateRef],{ngIf:[0,"ngIf"]},null),(n()(),t["ɵeld"](6,0,null,null,1,"span",[],null,null,null,null,null)),(n()(),t["ɵted"](7,null,["",""]))],function(n,l){n(l,5,0,l.parent.parent.parent.context.$implicit.viewCount)},function(n,l){var e=l.component;n(l,3,0,l.parent.parent.parent.context.$implicit.author),n(l,7,0,e.fromNow(l.parent.parent.parent.context.$implicit.publishTime))})}function E(n){return t["ɵvid"](0,[(n()(),t["ɵeld"](0,0,null,null,3,"div",[["class","footer-shareBtn-wrap"]],null,[[null,"click"]],function(n,l,e){var t=!0;return"click"===l&&(t=!1!==n.component.shareArticle(e,n.parent.parent.parent.parent.context.$implicit)&&t),t},null,null)),(n()(),t["ɵeld"](1,0,null,null,2,"span",[],null,null,null,null,null)),(n()(),t["ɵeld"](2,0,null,null,1,"ion-icon",[["name","arrow-redo-outline"]],null,null,null,i.vb,i.u)),t["ɵdid"](3,49152,null,0,a.D,[t.ChangeDetectorRef,t.ElementRef,t.NgZone],{name:[0,"name"]},null)],function(n,l){n(l,3,0,"arrow-redo-outline")},null)}function M(n){return t["ɵvid"](0,[(n()(),t["ɵeld"](0,0,null,null,1,"ion-icon",[["src","assets/icons/comment/icon-thumbs.svg"],["style","font-size: 19px"]],null,null,null,i.vb,i.u)),t["ɵdid"](1,49152,null,0,a.D,[t.ChangeDetectorRef,t.ElementRef,t.NgZone],{src:[0,"src"]},null)],function(n,l){n(l,1,0,"assets/icons/comment/icon-thumbs.svg")},null)}function $(n){return t["ɵvid"](0,[(n()(),t["ɵeld"](0,0,null,null,1,"ion-icon",[["src","assets/icons/comment/icon-thumbs-up.svg"],["style","font-size: 19px"]],null,null,null,i.vb,i.u)),t["ɵdid"](1,49152,null,0,a.D,[t.ChangeDetectorRef,t.ElementRef,t.NgZone],{src:[0,"src"]},null)],function(n,l){n(l,1,0,"assets/icons/comment/icon-thumbs-up.svg")},null)}function V(n){return t["ɵvid"](0,[(n()(),t["ɵeld"](0,0,null,null,14,"div",[["class","footer-voteIcon-wrap"]],null,null,null,null,null)),(n()(),t["ɵeld"](1,0,null,null,5,"span",[],null,null,null,null,null)),(n()(),t["ɵeld"](2,0,null,null,1,"ion-icon",[["name","eye-outline"]],null,null,null,i.vb,i.u)),t["ɵdid"](3,49152,null,0,a.D,[t.ChangeDetectorRef,t.ElementRef,t.NgZone],{name:[0,"name"]},null),(n()(),t["ɵeld"](4,0,null,null,2,"ion-label",[],null,null,null,i.Eb,i.D)),t["ɵdid"](5,49152,null,0,a.O,[t.ChangeDetectorRef,t.ElementRef,t.NgZone],null,null),(n()(),t["ɵted"](6,0,["",""])),(n()(),t["ɵeld"](7,0,null,null,7,"span",[],null,[[null,"click"]],function(n,l,e){var t=!0;return"click"===l&&(t=!1!==n.component.toggleLike(e,n.parent.parent.parent.parent.context.$implicit)&&t),t},null,null)),(n()(),t["ɵand"](16777216,null,null,1,null,M)),t["ɵdid"](9,16384,null,0,u.NgIf,[t.ViewContainerRef,t.TemplateRef],{ngIf:[0,"ngIf"]},null),(n()(),t["ɵand"](16777216,null,null,1,null,$)),t["ɵdid"](11,16384,null,0,u.NgIf,[t.ViewContainerRef,t.TemplateRef],{ngIf:[0,"ngIf"]},null),(n()(),t["ɵeld"](12,0,null,null,2,"ion-label",[],null,null,null,i.Eb,i.D)),t["ɵdid"](13,49152,null,0,a.O,[t.ChangeDetectorRef,t.ElementRef,t.NgZone],null,null),(n()(),t["ɵted"](14,0,["",""]))],function(n,l){n(l,3,0,"eye-outline"),n(l,9,0,!l.parent.parent.parent.parent.context.$implicit.isFavor),n(l,11,0,l.parent.parent.parent.parent.context.$implicit.isFavor)},function(n,l){n(l,6,0,l.parent.parent.parent.parent.context.$implicit.viewCount||0),n(l,14,0,l.parent.parent.parent.parent.context.$implicit.favorCount||0)})}function _(n){return t["ɵvid"](0,[(n()(),t["ɵeld"](0,0,null,null,8,"div",[["class","item-sub"]],null,null,null,null,null)),(n()(),t["ɵeld"](1,0,null,null,3,"div",[],null,null,null,null,null)),(n()(),t["ɵeld"](2,0,null,null,2,"span",[],null,null,null,null,null)),(n()(),t["ɵted"](3,null,[" "," "])),t["ɵppd"](4,2),(n()(),t["ɵand"](16777216,null,null,1,null,E)),t["ɵdid"](6,16384,null,0,u.NgIf,[t.ViewContainerRef,t.TemplateRef],{ngIf:[0,"ngIf"]},null),(n()(),t["ɵand"](16777216,null,null,1,null,V)),t["ɵdid"](8,16384,null,0,u.NgIf,[t.ViewContainerRef,t.TemplateRef],{ngIf:[0,"ngIf"]},null)],function(n,l){var e=l.component;n(l,6,0,e.isShowShare),n(l,8,0,e.isShowVote)},function(n,l){var e=t["ɵunv"](l,3,0,n(l,4,0,t["ɵnov"](l.parent.parent.parent.parent,0),l.parent.parent.parent.context.$implicit.publishTime,"yyyy-MM-dd"))||"时间未知";n(l,3,0,e)})}function A(n){return t["ɵvid"](0,[(n()(),t["ɵeld"](0,0,null,null,13,"div",[["class","card-item non-thumbnail-box"]],null,null,null,null,null)),(n()(),t["ɵeld"](1,0,null,null,5,"ion-card-header",[],null,null,null,i.ib,i.i)),t["ɵdid"](2,49152,null,0,a.p,[t.ChangeDetectorRef,t.ElementRef,t.NgZone],null,null),(n()(),t["ɵeld"](3,0,null,0,3,"ion-card-title",[],null,null,null,i.kb,i.k)),t["ɵdid"](4,49152,null,0,a.r,[t.ChangeDetectorRef,t.ElementRef,t.NgZone],null,null),(n()(),t["ɵeld"](5,0,null,0,1,"p",[["class","item-title"]],null,null,null,null,null)),(n()(),t["ɵted"](6,null,[" "," "])),(n()(),t["ɵeld"](7,0,null,null,1,"WhiteSpace",[],[[2,"am-whitespace",null],[2,"am-whitespace-xs",null],[2,"am-whitespace-sm",null],[2,"am-whitespace-md",null],[2,"am-whitespace-lg",null],[2,"am-whitespace-xl",null]],null,null,o.u,o.m)),t["ɵdid"](8,49152,null,0,r.Jc,[],{size:[0,"size"]},null),(n()(),t["ɵeld"](9,0,null,null,4,"ion-card-content",[],null,null,null,i.hb,i.h)),t["ɵdid"](10,49152,null,0,a.o,[t.ChangeDetectorRef,t.ElementRef,t.NgZone],null,null),(n()(),t["ɵand"](16777216,null,0,1,null,D)),t["ɵdid"](12,16384,null,0,u.NgIf,[t.ViewContainerRef,t.TemplateRef],{ngIf:[0,"ngIf"],ngIfElse:[1,"ngIfElse"]},null),(n()(),t["ɵand"](0,[["jiaLingUI",2]],0,0,null,_))],function(n,l){var e=l.component;n(l,8,0,"xl"),n(l,12,0,!e.jiaLingArticleListUI,t["ɵnov"](l,13))},function(n,l){n(l,6,0,l.parent.parent.context.$implicit.title),n(l,7,0,t["ɵnov"](l,8).amWhiteSpace,t["ɵnov"](l,8).amWhitespaceXs,t["ɵnov"](l,8).amWhitespaceSm,t["ɵnov"](l,8).amWhitespaceMd,t["ɵnov"](l,8).amWhitespaceLg,t["ɵnov"](l,8).amWhitespaceXl)})}function Z(n){return t["ɵvid"](0,[(n()(),t["ɵeld"](0,0,null,null,0,"img",[],[[8,"src",4]],null,null,null,null))],null,function(n,l){n(l,0,0,l.parent.parent.parent.context.$implicit.thumbnail)})}function U(n){return t["ɵvid"](0,[(n()(),t["ɵeld"](0,0,null,null,1,"span",[],null,null,null,null,null)),(n()(),t["ɵted"](1,null,["","阅读"]))],null,function(n,l){n(l,1,0,l.parent.parent.parent.parent.context.$implicit.viewCount||0)})}function F(n){return t["ɵvid"](0,[(n()(),t["ɵeld"](0,0,null,null,7,"div",[["class","item-sub"]],null,null,null,null,null)),(n()(),t["ɵeld"](1,0,null,null,6,"div",[],null,null,null,null,null)),(n()(),t["ɵeld"](2,0,null,null,1,"span",[],null,null,null,null,null)),(n()(),t["ɵted"](3,null,["",""])),(n()(),t["ɵand"](16777216,null,null,1,null,U)),t["ɵdid"](5,16384,null,0,u.NgIf,[t.ViewContainerRef,t.TemplateRef],{ngIf:[0,"ngIf"]},null),(n()(),t["ɵeld"](6,0,null,null,1,"span",[],null,null,null,null,null)),(n()(),t["ɵted"](7,null,["",""]))],function(n,l){n(l,5,0,l.parent.parent.parent.context.$implicit.viewCount)},function(n,l){var e=l.component;n(l,3,0,l.parent.parent.parent.context.$implicit.author),n(l,7,0,e.fromNow(l.parent.parent.parent.context.$implicit.publishTime))})}function z(n){return t["ɵvid"](0,[(n()(),t["ɵeld"](0,0,null,null,3,"div",[["class","footer-shareBtn-wrap"]],null,[[null,"click"]],function(n,l,e){var t=!0;return"click"===l&&(t=!1!==n.component.shareArticle(e,n.parent.parent.parent.parent.context.$implicit)&&t),t},null,null)),(n()(),t["ɵeld"](1,0,null,null,2,"span",[],null,null,null,null,null)),(n()(),t["ɵeld"](2,0,null,null,1,"ion-icon",[["name","arrow-redo-outline"]],null,null,null,i.vb,i.u)),t["ɵdid"](3,49152,null,0,a.D,[t.ChangeDetectorRef,t.ElementRef,t.NgZone],{name:[0,"name"]},null)],function(n,l){n(l,3,0,"arrow-redo-outline")},null)}function j(n){return t["ɵvid"](0,[(n()(),t["ɵeld"](0,0,null,null,1,"ion-icon",[["src","assets/icons/comment/icon-thumbs.svg"],["style","font-size: 19px"]],null,null,null,i.vb,i.u)),t["ɵdid"](1,49152,null,0,a.D,[t.ChangeDetectorRef,t.ElementRef,t.NgZone],{src:[0,"src"]},null)],function(n,l){n(l,1,0,"assets/icons/comment/icon-thumbs.svg")},null)}function W(n){return t["ɵvid"](0,[(n()(),t["ɵeld"](0,0,null,null,1,"ion-icon",[["src","assets/icons/comment/icon-thumbs-up.svg"],["style","font-size: 19px"]],null,null,null,i.vb,i.u)),t["ɵdid"](1,49152,null,0,a.D,[t.ChangeDetectorRef,t.ElementRef,t.NgZone],{src:[0,"src"]},null)],function(n,l){n(l,1,0,"assets/icons/comment/icon-thumbs-up.svg")},null)}function G(n){return t["ɵvid"](0,[(n()(),t["ɵeld"](0,0,null,null,14,"div",[["class","footer-voteIcon-wrap"]],null,null,null,null,null)),(n()(),t["ɵeld"](1,0,null,null,5,"span",[],null,null,null,null,null)),(n()(),t["ɵeld"](2,0,null,null,1,"ion-icon",[["name","eye-outline"]],null,null,null,i.vb,i.u)),t["ɵdid"](3,49152,null,0,a.D,[t.ChangeDetectorRef,t.ElementRef,t.NgZone],{name:[0,"name"]},null),(n()(),t["ɵeld"](4,0,null,null,2,"ion-label",[],null,null,null,i.Eb,i.D)),t["ɵdid"](5,49152,null,0,a.O,[t.ChangeDetectorRef,t.ElementRef,t.NgZone],null,null),(n()(),t["ɵted"](6,0,["",""])),(n()(),t["ɵeld"](7,0,null,null,7,"span",[],null,[[null,"click"]],function(n,l,e){var t=!0;return"click"===l&&(t=!1!==n.component.toggleLike(e,n.parent.parent.parent.parent.context.$implicit)&&t),t},null,null)),(n()(),t["ɵand"](16777216,null,null,1,null,j)),t["ɵdid"](9,16384,null,0,u.NgIf,[t.ViewContainerRef,t.TemplateRef],{ngIf:[0,"ngIf"]},null),(n()(),t["ɵand"](16777216,null,null,1,null,W)),t["ɵdid"](11,16384,null,0,u.NgIf,[t.ViewContainerRef,t.TemplateRef],{ngIf:[0,"ngIf"]},null),(n()(),t["ɵeld"](12,0,null,null,2,"ion-label",[],null,null,null,i.Eb,i.D)),t["ɵdid"](13,49152,null,0,a.O,[t.ChangeDetectorRef,t.ElementRef,t.NgZone],null,null),(n()(),t["ɵted"](14,0,["",""]))],function(n,l){n(l,3,0,"eye-outline"),n(l,9,0,!l.parent.parent.parent.parent.context.$implicit.isFavor),n(l,11,0,l.parent.parent.parent.parent.context.$implicit.isFavor)},function(n,l){n(l,6,0,l.parent.parent.parent.parent.context.$implicit.viewCount||0),n(l,14,0,l.parent.parent.parent.parent.context.$implicit.favorCount||0)})}function B(n){return t["ɵvid"](0,[(n()(),t["ɵeld"](0,0,null,null,8,"div",[["class","item-sub"]],null,null,null,null,null)),(n()(),t["ɵeld"](1,0,null,null,3,"div",[],null,null,null,null,null)),(n()(),t["ɵeld"](2,0,null,null,2,"span",[],null,null,null,null,null)),(n()(),t["ɵted"](3,null,[" "," "])),t["ɵppd"](4,2),(n()(),t["ɵand"](16777216,null,null,1,null,z)),t["ɵdid"](6,16384,null,0,u.NgIf,[t.ViewContainerRef,t.TemplateRef],{ngIf:[0,"ngIf"]},null),(n()(),t["ɵand"](16777216,null,null,1,null,G)),t["ɵdid"](8,16384,null,0,u.NgIf,[t.ViewContainerRef,t.TemplateRef],{ngIf:[0,"ngIf"]},null)],function(n,l){var e=l.component;n(l,6,0,e.isShowShare),n(l,8,0,e.isShowVote)},function(n,l){var e=t["ɵunv"](l,3,0,n(l,4,0,t["ɵnov"](l.parent.parent.parent.parent,0),l.parent.parent.parent.context.$implicit.publishTime,"yyyy-MM-dd"))||"时间未知";n(l,3,0,e)})}function J(n){return t["ɵvid"](0,[(n()(),t["ɵeld"](0,0,null,null,13,"div",[["class","card-item"]],null,null,null,null,null)),(n()(),t["ɵeld"](1,0,null,null,12,"ion-item",[["class","thumbnail-box"]],null,null,null,i.Db,i.z)),t["ɵdid"](2,49152,null,0,a.I,[t.ChangeDetectorRef,t.ElementRef,t.NgZone],{lines:[0,"lines"]},null),(n()(),t["ɵeld"](3,0,null,0,2,"div",[["class","thumbnail"]],[[4,"order",null]],null,null,null,null)),(n()(),t["ɵand"](16777216,null,null,1,null,Z)),t["ɵdid"](5,16384,null,0,u.NgIf,[t.ViewContainerRef,t.TemplateRef],{ngIf:[0,"ngIf"]},null),(n()(),t["ɵeld"](6,0,null,0,7,"div",[["class","right"]],null,null,null,null,null)),(n()(),t["ɵeld"](7,0,null,null,3,"ion-card-title",[],null,null,null,i.kb,i.k)),t["ɵdid"](8,49152,null,0,a.r,[t.ChangeDetectorRef,t.ElementRef,t.NgZone],null,null),(n()(),t["ɵeld"](9,0,null,0,1,"p",[["class","item-title"]],null,null,null,null,null)),(n()(),t["ɵted"](10,null,[" "," "])),(n()(),t["ɵand"](16777216,null,null,1,null,F)),t["ɵdid"](12,16384,null,0,u.NgIf,[t.ViewContainerRef,t.TemplateRef],{ngIf:[0,"ngIf"],ngIfElse:[1,"ngIfElse"]},null),(n()(),t["ɵand"](0,[["jiaLingUI",2]],null,0,null,B))],function(n,l){var e=l.component;n(l,2,0,"none"),n(l,5,0,l.parent.parent.context.$implicit.thumbnail),n(l,12,0,!e.jiaLingArticleListUI,t["ɵnov"](l,13))},function(n,l){n(l,3,0,l.component.jiaLingArticleListUI?-5:5),n(l,10,0,l.parent.parent.context.$implicit.title)})}function H(n){return t["ɵvid"](0,[(n()(),t["ɵeld"](0,0,null,null,4,null,null,null,null,null,null,null)),(n()(),t["ɵand"](16777216,null,null,1,null,A)),t["ɵdid"](2,16384,null,0,u.NgIf,[t.ViewContainerRef,t.TemplateRef],{ngIf:[0,"ngIf"]},null),(n()(),t["ɵand"](16777216,null,null,1,null,J)),t["ɵdid"](4,16384,null,0,u.NgIf,[t.ViewContainerRef,t.TemplateRef],{ngIf:[0,"ngIf"]},null),(n()(),t["ɵand"](0,null,null,0))],function(n,l){n(l,2,0,!l.parent.context.$implicit.thumbnail),n(l,4,0,l.parent.context.$implicit.thumbnail)},null)}function X(n){return t["ɵvid"](0,[(n()(),t["ɵeld"](0,0,null,null,7,"Card",[],[[2,"am-card",null],[2,"am-card-full",null]],[[null,"click"]],function(n,l,e){var t=!0;return"click"===l&&(t=!1!==n.component.openItem(n.context.$implicit)&&t),t},o.p,o.h)),t["ɵdid"](1,49152,null,0,r.A,[],{full:[0,"full"]},null),(n()(),t["ɵeld"](2,0,null,0,5,null,null,null,null,null,null,null)),t["ɵdid"](3,16384,null,0,u.NgSwitch,[],{ngSwitch:[0,"ngSwitch"]},null),(n()(),t["ɵand"](16777216,null,null,1,null,k)),t["ɵdid"](5,278528,null,0,u.NgSwitchCase,[t.ViewContainerRef,t.TemplateRef,u.NgSwitch],{ngSwitchCase:[0,"ngSwitchCase"]},null),(n()(),t["ɵand"](16777216,null,null,1,null,H)),t["ɵdid"](7,16384,null,0,u.NgSwitchDefault,[t.ViewContainerRef,t.TemplateRef,u.NgSwitch],null,null)],function(n,l){n(l,1,0,!0),n(l,3,0,l.context.$implicit.type),n(l,5,0,"video")},function(n,l){n(l,0,0,t["ɵnov"](l,1).cardWrapper,t["ɵnov"](l,1).cardFull)})}function Q(n){return t["ɵvid"](0,[t["ɵpid"](0,u.DatePipe,[t.LOCALE_ID]),(n()(),t["ɵand"](16777216,null,null,1,null,X)),t["ɵdid"](2,278528,null,0,u.NgForOf,[t.ViewContainerRef,t.TemplateRef,t.IterableDiffers],{ngForOf:[0,"ngForOf"]},null),(n()(),t["ɵeld"](3,0,null,null,1,"evm-page-tip",[],null,[[null,"refresh"]],function(n,l,e){var t=!0;return"refresh"===l&&(t=!1!==n.component.init()&&t),t},c.b,c.a)),t["ɵdid"](4,638976,null,0,s.d,[t.ElementRef,t.Renderer2],{payload:[0,"payload"]},{refresh:"refresh"})],function(n,l){var e=l.component;n(l,2,0,e.list),n(l,4,0,e.pageTipPayload)},null)}function Y(n){return t["ɵvid"](0,[(n()(),t["ɵeld"](0,0,null,null,1,"app-article-list",[],null,null,null,Q,w)),t["ɵdid"](1,770048,null,0,p.a,[d.a,f.a,m.b,g.m,t.ElementRef,h.a,v.a,b.a,C.a,I.a,g.a],null,null)],function(n,l){n(l,1,0)},null)}var q=t["ɵccf"]("app-article-list",p.a,Y,{columnId:"columnId",isGather:"isGather"},{},[])}}]);