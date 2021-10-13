(window.webpackJsonp=window.webpackJsonp||[]).push([[8],{"2apM":function(n,t,e){"use strict";e.r(t);var l=e("CcnG"),a=e("+SBZ"),i=e("h1wG"),o=e("KzzO"),u=e("O0u2"),s=e("fQKf"),r=e("909l"),c=e("F/XL"),d=e("atIC"),g=e("1FC6"),p=e("ucSS"),f=e("0Huk"),m=e("fCh1"),h=e("B0rH"),b=function(){function n(n,t,e,a,i,o,u,s,r,c,d,g,p){this.umsUserService=n,this.normalUserService=t,this.adminUserService=e,this.shortVideoService=a,this.logger=i,this.app=o,this.statsService=u,this.nativeBridgeService=s,this.alertCtrl=r,this.utils=c,this.router=d,this.activatedRoute=g,this.ngZone=p,this.tabIndex=0,this.tabsData=[],this.expired=!1,this.pageAction="init",this.messageChannel=new l.EventEmitter,this.isLoading=!1,this.isLogined=!0,this.isRoot=!0,this.cName=this.activatedRoute.snapshot.paramMap.get("name")||"乡音分享"}return n.prototype.ngOnInit=function(){var t=this;this.appChannelSubscription=this.app.appChannel.subscribe(function(e){if(t.logger.debug(n.TAG+" ngOnInit() app channel payload: "+JSON.stringify(e)),e.type===g.a.RELOAD&&(t.playingItem=null,t.expired=!0),e.type===g.a.USERCHANGED&&t.ngZone.run(function(){t.reset(),t.pageAction="init",t.pageUpdateType="all",t.playingItem=null,t.pageLoad()}),e.type===g.a.SYSTEMEVENT&&"LEAVE"===e.data.status&&t.pausePlaying(),e.type===g.a.UPDATELISTSTATS){var l=e.data;if(l&&"video"===l.type&&"comment"===l.statsType){var a=t.getCurrentTabData();if(a&&Array.isArray(a.list)){var i=a.list.find(function(n){return n.raw.id==l.id});i&&(i.commentCount=l.count)}}}});var e=+this.nativeBridgeService.invoke({method:m.b.METHOD_GET_CLIPBOARD});this.logger.info(n.TAG+" checkMode: "+e),e>0&&(this.nativeBridgeService.invoke({method:m.b.METHOD_SET_CLIPBOARD},0),this.tabIndex=2),this.pageAction="init",this.pageUpdateType="all",this.playingItem=null,this.pageLoad()},n.prototype.ngOnDestroy=function(){this.appChannelSubscription.unsubscribe()},n.prototype.ngAfterViewInit=function(){var t=this;this.messageChannel.subscribe(function(e){t.logger.info(n.TAG+" messageChannel payload: "+JSON.stringify(e)),t.logger.info(n.TAG+" messageChannel pageAction: "+t.pageAction),"refresh"===t.pageAction?(t.pageAction="complete",t.refresher.complete(),t.getCurrentTabData().infiniteScrollStatus=e.status,t.getCurrentTabData().pageTipPayload={status:e.status,message:e.message}):"more"===t.pageAction?(t.pageAction="complete",t.getCurrentTabData().infiniteScrollStatus=e.status):(t.pageAction="complete",t.getCurrentTabData().infiniteScrollStatus=e.status,t.getCurrentTabData().pageTipPayload={status:e.status,message:e.message})})},n.prototype.ionViewWillEnter=function(){this.isRoot=!document.querySelector("app-short-video.can-go-back"),this.isRoot&&this.nativeBridgeService.invoke({method:m.b.METHOD_SHOW_TAB}),this.expired&&(this.reset(),this.pageAction="init",this.pageUpdateType="local",this.pageLoad()),this.playingItem&&this.playingItem.vgApi.play()},n.prototype.ionViewWillLeave=function(){this.pausePlaying(),this.isRoot&&this.nativeBridgeService.invoke({method:m.b.METHOD_HIDE_TAB})},n.prototype.doRefresh=function(n){this.pageAction="refresh",this.pageUpdateType="all",this.resetInfiniteScroll(),this.playingItem=null,this.pageLoad()},n.prototype.doInfinite=function(t){this.logger.debug(n.TAG+" doInfinite() execute"),this.logger.debug(n.TAG+" doInfinite() infiniteScrollStatus: "+this.getCurrentTabData().infiniteScrollStatus),"empty"!==this.getCurrentTabData().infiniteScrollStatus?(this.pageAction="more",this.getCurrentTabData().infiniteScrollStatus="loading",this.loadTabContent(this.tabIndex)):this.infiniteScroll&&this.infiniteScroll.complete()},n.prototype.onTabClick=function(n){var t=n.detail.value,e=this.tabsData.findIndex(function(n){return n.name===t});this.tabIndex=e,this.loadTabContent(e)},n.prototype.getCurrentTabData=function(){return this.tabsData[this.tabIndex]},n.prototype.onDelete=function(n){var t=this.getCurrentTabData();t.list.splice(t.list.indexOf(n),1),0===t.list.length&&(t.pageTipPayload={status:"empty",message:"暂时没有发布的分享短视频！"}),this.pageAction="refresh"},n.prototype.shareShortVide=function(){var n=this;this.umsUserService.isLogined().subscribe(function(t){if(t){var e=n.normalUserService.user;switch(e.thirdPartyUserCheckingStatus){case o.b.PASSED:n.shortVideoService.getNormalUserShareUser().subscribe(function(t){t?n.nativeBridgeService.invoke({method:m.b.METHOD_SHORT_VIDEO_RECORD},e.userId,n.app.appConfig.ums.userType,"",t.durationLimit,0,"","","","",e.areaId):n.utils.toast("fail","您没有分享权限！")});break;case o.b.PENDING:n.utils.toast("info","您的归属地正在申请中，请耐心等待！");break;case o.b.REJECTED:n.utils.toast("info","您没有分享权限！")}}else n.presentAlert("请先注册或者登录后使用分享功能！",function(){n.goLogin()})})},n.prototype.goLogin=function(){this.nativeBridgeService.invoke({method:m.b.METHOD_LOGIN})},n.prototype.goLivePage=function(){this.router.navigate(["/standard/live",{name:"xishuangbanna"===this.app.appConfig.name?"我要上电视":"乡音直播"}],{replaceUrl:!0})},n.prototype.setPlayingItem=function(n){this.stopPlaying(),this.playingItem=n},n.prototype.stopPlaying=function(){this.playingItem&&this.playingItem.playing&&(this.playingItem.playing=!1)},n.prototype.pausePlaying=function(){this.playingItem&&this.playingItem.vgApi&&this.playingItem.vgApi.pause()},n.prototype.reset=function(){this.tabIndex=0,this.tabsData=[],this.expired=!1,this.isLoading=!1},n.prototype.pageLoad=function(){var n=this;this.normalUserService.initUser("all"===this.pageUpdateType).subscribe(function(t){t.userId&&(n.isLogined=!0),n.initTabsData(function(){n.loadTabContent(n.tabIndex)})})},n.prototype.initTabsData=function(t){var e=this;this.tabsData=[],this.tabsData.push({name:"最新",type:"recent",pageIndex:1,list:[],isInit:!1,pageTipPayload:{status:"loading"},infiniteScrollStatus:"complete"}),Object(r.b)(this.shortVideoService.getAdminShareUser("all"===this.pageUpdateType),Object(c.a)(this.normalUserService.user)).subscribe(function(l){var a=l[0],i=l[1];e.logger.info(n.TAG+" initTabsData() 获取普通用户权限: "+JSON.stringify(i)),e.logger.info(n.TAG+" initTabsData() 获取管理用户权限: "+JSON.stringify(a)),i&&i.thirdPartyUserCheckingStatus===o.b.PASSED&&e.tabsData.push({name:"我的",type:"my",pageIndex:1,list:[],isInit:!1,pageTipPayload:{status:"loading"},infiniteScrollStatus:"complete"}),a&&1===a.checkPower?e.tabsData.push({name:"我的管理",type:"manage",pageIndex:1,list:[],isInit:!1,pageTipPayload:{status:"loading"},infiniteScrollStatus:"complete"}):e.logger.warn(n.TAG+" initTabsData() 管理用户未登录或者权限不够！"),t(),e.logger.log(n.TAG+" initTabsData() tabs: "+JSON.stringify(e.tabsData))})},n.prototype.loadTabContent=function(n){switch(void 0===n&&(n=0),"more"!==this.pageAction&&this.tabsData.filter(function(t,e){return e===n}).forEach(function(n){n.pageIndex=1,n.list=[],n.isInit=!1,n.pageTipPayload={status:"loading"},n.infiniteScrollStatus="complete"}),this.stopPlaying(),this.tabsData[n].type){case"recent":this.getRecentVideoList();break;case"my":this.getMyVideoList();break;case"manage":this.getManageVideoList()}},n.prototype.getRecentVideoList=function(){var t=this;this.isLoading||(this.isLoading=!0,this.shortVideoService.getRecentVideoList(this.tabsData[this.tabIndex].pageIndex).subscribe(function(e){if(t.isLoading=!1,"success"===e.status){var l=e.data.map(function(n){return{title:n.name,area:n.uploadUserAreaName,imageUrl:n.imageUrl,avatar:n.umsUser?n.umsUser.imageUrl:null,author:n.umsUser?n.umsUser.name:null,playing:!1,raw:n}});t.tabsData[t.tabIndex].list=t.tabsData[t.tabIndex].list.concat(l),e.data.length<t.app.appConfig.pageSize?e.status="empty":t.tabsData[t.tabIndex].pageIndex++;var a=e.data.reduce(function(n,t){return""===n?t.id:n+","+t.id},""),i=t.normalUserService.user;i?t.statsService.fetchTotalCount({ids:a,favorTypeId:t.app.appConfig.vote.type.favor.video.typeId,commentTypeId:t.app.appConfig.vote.type.comment.video.typeId,userId:i.userId}).subscribe(function(e){t.logger.debug(n.TAG+" fetchTotalCount() res: "+JSON.stringify(e)),e&&l.forEach(function(n){var t=e.find(function(t){return t.id===n.raw.id});t&&(n.favorCount=t.favorCount,n.commentCount=t.commentCount,n.isFavor=t.isFavor)})}):t.logger.warn(n.TAG+" getRecentVideoList() 获取统计数据查询普通用户不存在")}else"init"!==t.pageAction&&"refresh"!==t.pageAction||(t.tabsData[t.tabIndex].list=[]),"用户不存在"===e.message?(e.status="empty",e.message="无权限浏览该区域下短视频内容！"):"empty"===e.status&&(e.message="来分享您的短视频吧");t.messageChannel.emit({status:e.status,message:e.message})}))},n.prototype.getMyVideoList=function(){var n=this;this.isLoading||(this.isLoading=!0,this.shortVideoService.getMyVideoList(this.tabsData[this.tabIndex].pageIndex).subscribe(function(t){if(n.isLoading=!1,"success"===t.status){var e=n.normalUserService.user;if(e){var l=t.data.map(function(n){return{title:n.name,area:n.uploadUserAreaName,imageUrl:n.imageUrl,avatar:e.umsUser?e.umsUser.avatar:null,author:e.umsUser?e.umsUser.name:null,raw:n}});n.tabsData[n.tabIndex].list=n.tabsData[n.tabIndex].list.concat(l),t.data.length<n.app.appConfig.pageSize?t.status="empty":n.tabsData[n.tabIndex].pageIndex++,n.messageChannel.emit({status:t.status})}else n.messageChannel.emit({status:"error"})}else"init"!==n.pageAction&&"refresh"!==n.pageAction||(n.tabsData[n.tabIndex].list=[]),"用户不存在"===t.message?(t.status="empty",t.message="无权限浏览该区域下短视频内容！"):"empty"===t.status&&(t.message="暂时没有发布的分享短视频！"),n.messageChannel.emit({status:t.status,message:t.message})}))},n.prototype.getManageVideoList=function(){var n=this;this.isLoading||(this.isLoading=!0,this.shortVideoService.getManageVideoList(this.tabsData[this.tabIndex].pageIndex).subscribe(function(t){if(n.isLoading=!1,"success"===t.status){n.tabsData[n.tabIndex].isInit=!0;var e=t.data.map(function(n){return{title:n.name,area:n.uploadUserAreaName,imageUrl:n.imageUrl,avatar:n.umsUser?n.umsUser.imageUrl:null,author:n.umsUser?n.umsUser.name:null,raw:n}});n.tabsData[n.tabIndex].list=n.tabsData[n.tabIndex].list.concat(e),t.data.length<n.app.appConfig.pageSize?t.status="empty":n.tabsData[n.tabIndex].pageIndex++,n.messageChannel.emit({status:t.status})}else"init"!==n.pageAction&&"refresh"!==n.pageAction||(n.tabsData[n.tabIndex].list=[]),n.messageChannel.emit({status:t.status,message:t.message})}))},n.prototype.resetInfiniteScroll=function(){this.getCurrentTabData().infiniteScrollStatus="complete"},n.prototype.presentAlert=function(n,t){this.alertCtrl.create({header:"提示",message:n,buttons:[{text:"确认",handler:function(){t()}},{text:"取消"}]}).then(function(n){n.present()})},n.TAG="ShortVideoComponent",n}(),v=function(){return function(){}}(),C=e("pMnS"),y=e("sq0T"),x=e("r7hO"),I=e("ZYCi"),S=e("wvhG"),O=e("U3FM"),D=e("MKJQ"),P=e("jy/b"),M=e("gIcY"),T=e("Ip0R"),R=e("KlRW"),_=e("o/zX"),w=e("lbwW"),A=e("DRql"),L=e("jc1A"),V=e("qMUB"),E=e("4tQ3"),U=e("TN67"),N=e("57in"),k=e("xvo6"),F=e("CYnb"),G=l["ɵcrt"]({encapsulation:0,styles:[['.main[_ngcontent-%COMP%]{display:flex;flex-direction:column;position:absolute;left:0;top:0;right:0;bottom:0}.main[_ngcontent-%COMP%]   .link-btn[_ngcontent-%COMP%]{width:100px;position:absolute;right:16px;top:16px}.main[_ngcontent-%COMP%]   .link-btn[_ngcontent-%COMP%]   img[_ngcontent-%COMP%]{width:100px}.main[_ngcontent-%COMP%]   .service-catalog-container[_ngcontent-%COMP%]{flex:none;width:100%;position:relative;background:url(top-bg.dc1f5ae1ea3f8b909204.png) left top/100% 100% no-repeat #fff}.main[_ngcontent-%COMP%]   .service-catalog-container[_ngcontent-%COMP%]   .inner[_ngcontent-%COMP%]{height:150px}.main[_ngcontent-%COMP%]   .service-catalog-container[_ngcontent-%COMP%]   .live-publish-btn[_ngcontent-%COMP%]{position:absolute;background:url(publish-share.a0cba25923c3eb744a63.png) left top/auto 100% no-repeat;width:80px;height:80px;bottom:0;left:50%;margin-left:-40px;margin-bottom:-40px}.main[_ngcontent-%COMP%]   .service-catalog-container[_ngcontent-%COMP%]   p[_ngcontent-%COMP%]{position:absolute;left:50%;bottom:0;margin-left:-28px;margin-bottom:-50px}.main[_ngcontent-%COMP%]   .user-action[_ngcontent-%COMP%]{flex:none;display:flex;padding:64px 16px 0;background:#fff}.main[_ngcontent-%COMP%]   .user-action[_ngcontent-%COMP%]   span[_ngcontent-%COMP%]{flex-grow:1}.main[_ngcontent-%COMP%]   .user-action[_ngcontent-%COMP%]   span[_ngcontent-%COMP%]:before{display:block;content:"";padding-top:32%}.main[_ngcontent-%COMP%]   .user-action[_ngcontent-%COMP%]   .user-action-share[_ngcontent-%COMP%]{background:url(daixiang-nav2-a.556cb43717bfd7a4de33.png) left top/100% auto no-repeat}.main[_ngcontent-%COMP%]   .user-action[_ngcontent-%COMP%]   .user-action-live[_ngcontent-%COMP%]{background:url(daixiang-nav1-a.82d3e8e10bc8b1e9277b.png) left top/100% auto no-repeat}.main[_ngcontent-%COMP%]   .user-action[_ngcontent-%COMP%]   .user-action-share.active[_ngcontent-%COMP%]{background-image:url(daixiang-nav2-f.5fafd02343d6f2a36bee.png)}.main[_ngcontent-%COMP%]   .user-action[_ngcontent-%COMP%]   .user-action-live.active[_ngcontent-%COMP%]{background-image:url(daixiang-nav1-f.dfc9ceb5f46e6e85e021.png)}.main[_ngcontent-%COMP%]   .view-box[_ngcontent-%COMP%]{display:flex;flex-direction:column;flex:1}.main[_ngcontent-%COMP%]   .view-box[_ngcontent-%COMP%]   .ums-unlogin-view[_ngcontent-%COMP%]{flex:1;display:flex;flex-direction:column;justify-content:center;align-items:center}.main[_ngcontent-%COMP%]   .view-box[_ngcontent-%COMP%]   .ums-unlogin-view[_ngcontent-%COMP%]   p[_ngcontent-%COMP%]{margin-bottom:5px}.main[_ngcontent-%COMP%]   .tabs[_ngcontent-%COMP%]{display:flex;align-items:flex-start;flex:none;padding:0 16px}.main[_ngcontent-%COMP%]   .tab-content[_ngcontent-%COMP%]{display:flex;flex:10;flex-direction:column}.main[_ngcontent-%COMP%]   .tab-content[_ngcontent-%COMP%]   .content-box[_ngcontent-%COMP%]{display:flex;flex:1;flex-direction:column}.main[_ngcontent-%COMP%]   .tab-content[_ngcontent-%COMP%]   .content-box[_ngcontent-%COMP%]  .header .title, .main[_ngcontent-%COMP%]   .tab-content[_ngcontent-%COMP%]   .content-box[_ngcontent-%COMP%]  .title-box .title{text-shadow:0 0 3px #000}.main[_ngcontent-%COMP%]   .tab-content[_ngcontent-%COMP%]   evm-page-tip[_ngcontent-%COMP%]{display:flex;flex:1}']],data:{}});function B(n){return l["ɵvid"](0,[(n()(),l["ɵeld"](0,0,null,null,1,"app-entry-header",[],null,null,null,y.b,y.a)),l["ɵdid"](1,114688,null,0,x.a,[i.a,I.m,S.a,O.b,m.b],null,null)],function(n,t){n(t,1,0)},null)}function Z(n){return l["ɵvid"](0,[(n()(),l["ɵeld"](0,0,null,null,3,"ion-buttons",[["slot","start"]],null,null,null,D.gb,D.f)),l["ɵdid"](1,49152,null,0,P.m,[l.ChangeDetectorRef,l.ElementRef,l.NgZone],null,null),(n()(),l["ɵeld"](2,0,null,0,1,"ion-icon",[["name","chevron-back-outline"]],null,[[null,"click"]],function(n,t,e){var l=!0;return"click"===t&&(l=!1!==n.component.app.back()&&l),l},D.vb,D.u)),l["ɵdid"](3,49152,null,0,P.D,[l.ChangeDetectorRef,l.ElementRef,l.NgZone],{name:[0,"name"]},null),(n()(),l["ɵeld"](4,0,null,null,2,"ion-title",[],null,null,null,D.Zb,D.Y)),l["ɵdid"](5,49152,null,0,P.yb,[l.ChangeDetectorRef,l.ElementRef,l.NgZone],null,null),(n()(),l["ɵted"](6,0,[" "," "]))],function(n,t){n(t,3,0,"chevron-back-outline")},function(n,t){n(t,6,0,t.component.cName)})}function H(n){return l["ɵvid"](0,[(n()(),l["ɵeld"](0,0,null,null,3,"ion-refresher",[["slot","fixed"]],null,[[null,"ionRefresh"]],function(n,t,e){var l=!0;return"ionRefresh"===t&&(l=!1!==n.component.doRefresh(e)&&l),l},D.Mb,D.K)),l["ɵdid"](1,49152,[[1,4]],0,P.bb,[l.ChangeDetectorRef,l.ElementRef,l.NgZone],null,null),(n()(),l["ɵeld"](2,0,null,0,1,"ion-refresher-content",[],null,null,null,D.Lb,D.L)),l["ɵdid"](3,49152,null,0,P.cb,[l.ChangeDetectorRef,l.ElementRef,l.NgZone],null,null)],null,null)}function $(n){return l["ɵvid"](0,[(n()(),l["ɵeld"](0,0,null,null,4,"ion-segment-button",[],null,null,null,D.Qb,D.Q)),l["ɵdid"](1,49152,null,0,P.kb,[l.ChangeDetectorRef,l.ElementRef,l.NgZone],{value:[0,"value"]},null),(n()(),l["ɵeld"](2,0,null,0,2,"ion-label",[],null,null,null,D.Eb,D.D)),l["ɵdid"](3,49152,null,0,P.O,[l.ChangeDetectorRef,l.ElementRef,l.NgZone],null,null),(n()(),l["ɵted"](4,0,["",""]))],function(n,t){n(t,1,0,t.context.$implicit.name)},function(n,t){n(t,4,0,t.context.$implicit.name)})}function J(n){return l["ɵvid"](0,[(n()(),l["ɵeld"](0,0,null,null,6,"div",[["class","tabs"]],null,null,null,null,null)),(n()(),l["ɵeld"](1,0,null,null,5,"ion-segment",[],null,[[null,"ionChange"],[null,"ionBlur"]],function(n,t,e){var a=!0,i=n.component;return"ionBlur"===t&&(a=!1!==l["ɵnov"](n,4)._handleBlurEvent(e.target)&&a),"ionChange"===t&&(a=!1!==l["ɵnov"](n,4)._handleChangeEvent(e.target)&&a),"ionChange"===t&&(a=!1!==i.onTabClick(e)&&a),a},D.Rb,D.P)),l["ɵprd"](5120,null,M.m,function(n){return[n]},[P.Lb]),l["ɵdid"](3,49152,null,0,P.jb,[l.ChangeDetectorRef,l.ElementRef,l.NgZone],{value:[0,"value"]},null),l["ɵdid"](4,16384,null,0,P.Lb,[l.ElementRef],null,null),(n()(),l["ɵand"](16777216,null,0,1,null,$)),l["ɵdid"](6,278528,null,0,T.NgForOf,[l.ViewContainerRef,l.TemplateRef,l.IterableDiffers],{ngForOf:[0,"ngForOf"]},null)],function(n,t){var e=t.component;n(t,3,0,null==e.tabsData[e.tabIndex]?null:e.tabsData[e.tabIndex].name),n(t,6,0,e.tabsData)},null)}function z(n){return l["ɵvid"](0,[(n()(),l["ɵeld"](0,0,null,null,1,"app-recent-video-item",[],null,[[null,"play"]],function(n,t,e){var l=!0;return"play"===t&&(l=!1!==n.component.setPlayingItem(e)&&l),l},R.b,R.a)),l["ɵdid"](1,245760,null,0,_.a,[P.Eb,w.a,d.a,h.a,P.Gb],{data:[0,"data"]},{play:"play"})],function(n,t){n(t,1,0,t.parent.context.$implicit)},null)}function j(n){return l["ɵvid"](0,[(n()(),l["ɵeld"](0,0,null,null,1,"app-my-video-item",[],null,[[null,"delete"]],function(n,t,e){var l=!0;return"delete"===t&&(l=!1!==n.component.onDelete(e)&&l),l},A.b,A.a)),l["ɵdid"](1,114688,null,0,L.a,[m.b,P.b,i.a,w.a,d.a,h.a],{data:[0,"data"]},{delete:"delete"})],function(n,t){n(t,1,0,t.parent.context.$implicit)},null)}function q(n){return l["ɵvid"](0,[(n()(),l["ɵeld"](0,0,null,null,1,"app-manage-video-item",[],null,null,null,V.b,V.a)),l["ɵdid"](1,245760,null,0,E.a,[m.b,P.a,h.a,w.a,u.a,d.a,U.b,I.m,s.a],{data:[0,"data"]},null)],function(n,t){n(t,1,0,t.parent.context.$implicit)},null)}function K(n){return l["ɵvid"](0,[(n()(),l["ɵeld"](0,0,null,null,8,null,null,null,null,null,null,null)),(n()(),l["ɵeld"](1,0,null,null,7,null,null,null,null,null,null,null)),l["ɵdid"](2,16384,null,0,T.NgSwitch,[],{ngSwitch:[0,"ngSwitch"]},null),(n()(),l["ɵand"](16777216,null,null,1,null,z)),l["ɵdid"](4,278528,null,0,T.NgSwitchCase,[l.ViewContainerRef,l.TemplateRef,T.NgSwitch],{ngSwitchCase:[0,"ngSwitchCase"]},null),(n()(),l["ɵand"](16777216,null,null,1,null,j)),l["ɵdid"](6,278528,null,0,T.NgSwitchCase,[l.ViewContainerRef,l.TemplateRef,T.NgSwitch],{ngSwitchCase:[0,"ngSwitchCase"]},null),(n()(),l["ɵand"](16777216,null,null,1,null,q)),l["ɵdid"](8,278528,null,0,T.NgSwitchCase,[l.ViewContainerRef,l.TemplateRef,T.NgSwitch],{ngSwitchCase:[0,"ngSwitchCase"]},null),(n()(),l["ɵand"](0,null,null,0))],function(n,t){n(t,2,0,null==t.parent.context.$implicit?null:t.parent.context.$implicit.type),n(t,4,0,"recent"),n(t,6,0,"my"),n(t,8,0,"manage")},null)}function W(n){return l["ɵvid"](0,[(n()(),l["ɵeld"](0,0,null,null,1,"evm-page-tip",[],null,[[null,"refresh"]],function(n,t,e){var l=!0;return"refresh"===t&&(l=!1!==n.component.pageLoad()&&l),l},N.b,N.a)),l["ɵdid"](1,638976,null,0,k.d,[l.ElementRef,l.Renderer2],{payload:[0,"payload"]},{refresh:"refresh"})],function(n,t){n(t,1,0,null==t.parent.context.$implicit?null:t.parent.context.$implicit.pageTipPayload)},null)}function Q(n){return l["ɵvid"](0,[(n()(),l["ɵeld"](0,0,null,null,1,"app-custom-infinite-scroll",[],null,[[null,"ionInfinite"]],function(n,t,e){var l=!0;return"ionInfinite"===t&&(l=!1!==n.component.doInfinite(e)&&l),l},F.b,F.a)),l["ɵdid"](1,4833280,[[2,4]],0,p.a,[O.b],{status:[0,"status"]},{ionInfinite:"ionInfinite"})],function(n,t){var e=t.component;n(t,1,0,null==e.tabsData[e.tabIndex]?null:e.tabsData[e.tabIndex].infiniteScrollStatus)},null)}function Y(n){return l["ɵvid"](0,[(n()(),l["ɵeld"](0,0,null,null,7,null,null,null,null,null,null,null)),(n()(),l["ɵeld"](1,0,null,null,6,"div",[["class","content-box"]],[[2,"hidden",null]],null,null,null,null)),(n()(),l["ɵand"](16777216,null,null,1,null,K)),l["ɵdid"](3,278528,null,0,T.NgForOf,[l.ViewContainerRef,l.TemplateRef,l.IterableDiffers],{ngForOf:[0,"ngForOf"]},null),(n()(),l["ɵand"](16777216,null,null,1,null,W)),l["ɵdid"](5,16384,null,0,T.NgIf,[l.ViewContainerRef,l.TemplateRef],{ngIf:[0,"ngIf"]},null),(n()(),l["ɵand"](16777216,null,null,1,null,Q)),l["ɵdid"](7,16384,null,0,T.NgIf,[l.ViewContainerRef,l.TemplateRef],{ngIf:[0,"ngIf"]},null)],function(n,t){var e=t.component;n(t,3,0,null==t.context.$implicit?null:t.context.$implicit.list),n(t,5,0,!(null!=t.context.$implicit&&null!=t.context.$implicit.list&&t.context.$implicit.list.length)),n(t,7,0,null==e.tabsData[e.tabIndex]?null:null==e.tabsData[e.tabIndex].list?null:e.tabsData[e.tabIndex].list.length)},function(n,t){n(t,1,0,t.context.index!==t.component.tabIndex)})}function X(n){return l["ɵvid"](0,[(n()(),l["ɵeld"](0,0,null,null,5,null,null,null,null,null,null,null)),(n()(),l["ɵand"](16777216,null,null,1,null,J)),l["ɵdid"](2,16384,null,0,T.NgIf,[l.ViewContainerRef,l.TemplateRef],{ngIf:[0,"ngIf"]},null),(n()(),l["ɵeld"](3,0,null,null,2,"div",[["class","tab-content"]],null,null,null,null,null)),(n()(),l["ɵand"](16777216,null,null,1,null,Y)),l["ɵdid"](5,278528,null,0,T.NgForOf,[l.ViewContainerRef,l.TemplateRef,l.IterableDiffers],{ngForOf:[0,"ngForOf"]},null)],function(n,t){var e=t.component;n(t,2,0,(null==e.tabsData?null:e.tabsData.length)>1),n(t,5,0,e.tabsData)},null)}function nn(n){return l["ɵvid"](0,[(n()(),l["ɵeld"](0,0,null,null,5,"div",[["class","ums-unlogin-view"]],null,null,null,null,null)),(n()(),l["ɵeld"](1,0,null,null,1,"p",[],null,null,null,null,null)),(n()(),l["ɵted"](-1,null,["游客身份不能使用乡音分享"])),(n()(),l["ɵeld"](3,0,null,null,2,"ion-button",[["shape","round"]],null,[[null,"click"]],function(n,t,e){var l=!0;return"click"===t&&(l=!1!==n.component.goLogin()&&l),l},D.fb,D.e)),l["ɵdid"](4,49152,null,0,P.l,[l.ChangeDetectorRef,l.ElementRef,l.NgZone],{shape:[0,"shape"]},null),(n()(),l["ɵted"](-1,0,["请登录"]))],function(n,t){n(t,4,0,"round")},null)}function tn(n){return l["ɵvid"](0,[l["ɵqud"](671088640,1,{refresher:0}),l["ɵqud"](671088640,2,{infiniteScroll:0}),(n()(),l["ɵeld"](2,0,null,null,6,"ion-header",[],null,null,null,D.ub,D.t)),l["ɵdid"](3,49152,null,0,P.C,[l.ChangeDetectorRef,l.ElementRef,l.NgZone],null,null),(n()(),l["ɵeld"](4,0,null,0,4,"ion-toolbar",[],null,null,null,D.bc,D.ab)),l["ɵdid"](5,49152,null,0,P.Ab,[l.ChangeDetectorRef,l.ElementRef,l.NgZone],{mode:[0,"mode"]},null),(n()(),l["ɵand"](16777216,null,0,1,null,B)),l["ɵdid"](7,16384,null,0,T.NgIf,[l.ViewContainerRef,l.TemplateRef],{ngIf:[0,"ngIf"],ngIfElse:[1,"ngIfElse"]},null),(n()(),l["ɵand"](0,[["nonRootView",2]],0,0,null,Z)),(n()(),l["ɵeld"](9,0,null,null,19,"ion-content",[],null,null,null,D.ob,D.n)),l["ɵdid"](10,49152,null,0,P.v,[l.ChangeDetectorRef,l.ElementRef,l.NgZone],null,null),(n()(),l["ɵand"](16777216,null,0,1,null,H)),l["ɵdid"](12,16384,null,0,T.NgIf,[l.ViewContainerRef,l.TemplateRef],{ngIf:[0,"ngIf"]},null),(n()(),l["ɵeld"](13,0,null,0,15,"div",[["class","main"]],null,null,null,null,null)),(n()(),l["ɵeld"](14,0,null,null,7,"div",[["class","service-catalog-container"]],null,null,null,null,null)),(n()(),l["ɵeld"](15,0,null,null,6,"div",[["class","inner"]],null,null,null,null,null)),(n()(),l["ɵeld"](16,0,null,null,1,"div",[["class","link-btn link-btn-live"]],null,[[null,"click"]],function(n,t,e){var l=!0;return"click"===t&&(l=!1!==n.component.goLivePage()&&l),l},null,null)),(n()(),l["ɵeld"](17,0,null,null,0,"img",[["src","assets/images/standard/short-video/daixiang-btn-live.png"]],null,null,null,null,null)),(n()(),l["ɵeld"](18,0,null,null,3,"div",[],null,[[null,"click"]],function(n,t,e){var l=!0;return"click"===t&&(l=!1!==n.component.shareShortVide()&&l),l},null,null)),(n()(),l["ɵeld"](19,0,null,null,0,"div",[["class","live-publish-btn"]],null,null,null,null,null)),(n()(),l["ɵeld"](20,0,null,null,1,"p",[],null,null,null,null,null)),(n()(),l["ɵted"](-1,null,["我要分享"])),(n()(),l["ɵeld"](22,0,null,null,2,"div",[["class","user-action"]],null,null,null,null,null)),(n()(),l["ɵeld"](23,0,null,null,0,"span",[["class","user-action-share active"]],null,null,null,null,null)),(n()(),l["ɵeld"](24,0,null,null,0,"span",[["class","user-action-live"]],null,[[null,"click"]],function(n,t,e){var l=!0;return"click"===t&&(l=!1!==n.component.goLivePage()&&l),l},null,null)),(n()(),l["ɵeld"](25,0,null,null,3,"div",[["class","view-box"]],null,null,null,null,null)),(n()(),l["ɵand"](16777216,null,null,1,null,X)),l["ɵdid"](27,16384,null,0,T.NgIf,[l.ViewContainerRef,l.TemplateRef],{ngIf:[0,"ngIf"],ngIfElse:[1,"ngIfElse"]},null),(n()(),l["ɵand"](0,[["umsUnloginView",2]],null,0,null,nn))],function(n,t){var e=t.component;n(t,5,0,e.isRoot?"md":"ios"),n(t,7,0,e.isRoot,l["ɵnov"](t,8)),n(t,12,0,null==e.tabsData?null:e.tabsData.length),n(t,27,0,e.isLogined,l["ɵnov"](t,28))},null)}function en(n){return l["ɵvid"](0,[(n()(),l["ɵeld"](0,0,null,null,1,"app-short-video",[],null,null,null,tn,G)),l["ɵdid"](1,4440064,null,0,b,[a.a,i.a,u.a,s.a,O.b,d.a,f.a,m.b,P.b,h.a,I.m,I.a,l.NgZone],null,null)],function(n,t){n(t,1,0)},null)}var ln=l["ɵccf"]("app-short-video",b,en,{},{},[]),an=e("mviv"),on=e("nWGD"),un=e("eXUs"),sn=e("3+oq"),rn=e("PqVL"),cn=e("YBzL"),dn=e("zi6J"),gn=e("LKh9"),pn=e("8zTQ"),fn=e("tFwg"),mn=e("wrzv"),hn=e("Cct2"),bn=e("yH+W"),vn=e("/7xp"),Cn=e("ovjX"),yn=e("utMG"),xn=e("iPGB");e.d(t,"ShortVideoModuleNgFactory",function(){return In});var In=l["ɵcmf"](v,[],function(n){return l["ɵmod"]([l["ɵmpd"](512,l.ComponentFactoryResolver,l["ɵCodegenComponentFactoryResolver"],[[8,[C.a,ln]],[3,l.ComponentFactoryResolver],l.NgModuleRef]),l["ɵmpd"](4608,T.NgLocalization,T.NgLocaleLocalization,[l.LOCALE_ID,[2,T["ɵangular_packages_common_common_a"]]]),l["ɵmpd"](4608,P.c,P.c,[l.NgZone,l.ApplicationRef]),l["ɵmpd"](4608,P.Fb,P.Fb,[P.c,l.ComponentFactoryResolver,l.Injector]),l["ɵmpd"](4608,P.Jb,P.Jb,[P.c,l.ComponentFactoryResolver,l.Injector]),l["ɵmpd"](4608,an.VgAPI,an.VgAPI,[]),l["ɵmpd"](4608,on.VgFullscreenAPI,on.VgFullscreenAPI,[]),l["ɵmpd"](4608,un.VgUtils,un.VgUtils,[]),l["ɵmpd"](4608,sn.VgControlsHidden,sn.VgControlsHidden,[]),l["ɵmpd"](4608,M.y,M.y,[]),l["ɵmpd"](1073742336,T.CommonModule,T.CommonModule,[]),l["ɵmpd"](1073742336,I.n,I.n,[[2,I.s],[2,I.m]]),l["ɵmpd"](1073742336,P.Cb,P.Cb,[]),l["ɵmpd"](1073742336,rn.cb,rn.cb,[]),l["ɵmpd"](1073742336,rn.vb,rn.vb,[]),l["ɵmpd"](1073742336,cn.a,cn.a,[]),l["ɵmpd"](1073742336,dn.a,dn.a,[]),l["ɵmpd"](1073742336,gn.VgCoreModule,gn.VgCoreModule,[]),l["ɵmpd"](1073742336,pn.VgControlsModule,pn.VgControlsModule,[]),l["ɵmpd"](1073742336,fn.VgOverlayPlayModule,fn.VgOverlayPlayModule,[]),l["ɵmpd"](1073742336,mn.VgBufferingModule,mn.VgBufferingModule,[]),l["ɵmpd"](1073742336,hn.a,hn.a,[]),l["ɵmpd"](1073742336,bn.a,bn.a,[]),l["ɵmpd"](1073742336,vn.a,vn.a,[]),l["ɵmpd"](1073742336,rn.Mc,rn.Mc,[]),l["ɵmpd"](1073742336,rn.Kc,rn.Kc,[]),l["ɵmpd"](1073742336,M.x,M.x,[]),l["ɵmpd"](1073742336,M.i,M.i,[]),l["ɵmpd"](1073742336,rn.kb,rn.kb,[]),l["ɵmpd"](1073742336,rn.p,rn.p,[]),l["ɵmpd"](1073742336,k.b,k.b,[]),l["ɵmpd"](1073742336,Cn.a,Cn.a,[]),l["ɵmpd"](1073742336,yn.a,yn.a,[]),l["ɵmpd"](1073742336,xn.a,xn.a,[]),l["ɵmpd"](1073742336,v,v,[]),l["ɵmpd"](1024,I.k,function(){return[[{path:"",component:b}]]},[])])})}}]);