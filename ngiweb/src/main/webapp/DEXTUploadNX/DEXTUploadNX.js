﻿;;
// 정품 or 평가판 인증키 입니다.
//var vNXAuthKey = "OOrQINl2oYUGoxlafEY1h6oQr0vnbG5o4QPSSOv1QsPQUl4d/3PSCFNhW5OzGMif3KA2Cv7b073GR9bbYVo0bwnudmbVqtJALpQsIpKn1V3/evQXqvmES4i+ut0lCEAFTrkTsCSZK8iuQVS9wXGeIQ==";
var vNXAuthKey = "ZU09P79nwe/ed0umPqP2NUqdTvovMChGpnkmQsUuXsigr4+E9JtFyBFGrEQ6AbfhASzEfzFwKSvcymv3dp62X2Xq2jeraBYJHG5uz4jHhndHSlkP5/xPHids0rToE+ACzZbbqLbiMKNl3VducQiTfYXA+s2JHzqb3mBgM0LpYnY=";

// http or https 사용 Scheme 를 지정합니다. (http:// or https://)
var vDEXTUploadNX_Scheme = "http://";

// ip 또는 hostname 입력(ex: 127.0.0.1, localhost, www.hostname.com)
//var vDEXTUploadNX_Domain = "change.ngii.go.kr";
var vDEXTUploadNX_Domain = "localhost:8080";

// DEXTUploadNX가 설치된 가상경로 입력(예: http://localhost/A/B/C/DEXTUploadNX 의 경로에 설치되어 있다면 /A/B/C/DEXTUploadNX 입력)
// 예제의 경우 iis 응용프로그램(가상디렉토리를) NXSamples로 등록하였고 NXSamples 경로 아래에 DEXTUploadNX 폴더를 동일하게
// 복사하여 사용하므로 /NXSamples/DEXTUploadNX 입력
var vDEXTUploadNX_Path = "/ngiweb/DEXTUploadNX";

// 최초 설치 또는 Client 버전 변경 시 DEXTUploadNX Client Plugin Dll을 설치하기 위한 페이지의 접근 URL입니다.
// vDEXTUploadNX_Domain 과 vDEXTUploadNX_Path 의 조합으로 아래에서 자동적으로 생성됩니다.
var vDEXTUploadNX_InstallPageURI = "";

// 최초 설치 또는 Client 버전 변경 시 설치페이지에서 참조하는 설치파일 경로 URL입니다.
// vDEXTUploadNX_Domain 과 vDEXTUploadNX_Path 의 조합으로 아래에서 자동적으로 생성됩니다.
var vDEXTUploadNX_InstallFileURI = "";

// 다국어 제공 XML 파일의 접근 URL 입니다.
// vDEXTUploadNX_Domain 과 vDEXTUploadNX_Path 의 조합으로 아래에서 자동적으로 생성됩니다.
var vDEXTUploadNX_MessageXMLURI = "";

// 버전 입력(임의로 수정하여 일치하지 않는 경우 Client 설치가 계속 발생할 수 있습니다.)
var vDEXTUploadNX_Version = "1.0.7.0";

if (vDEXTUploadNX_Domain != "") {
	vDEXTUploadNX_InstallPageURI = vDEXTUploadNX_Scheme + vDEXTUploadNX_Domain + vDEXTUploadNX_Path + "/DEXTUploadNX_Client_Install.html";
	vDEXTUploadNX_InstallFileURI = vDEXTUploadNX_Scheme + vDEXTUploadNX_Domain + vDEXTUploadNX_Path + "/DEXTUploadNX_Client_Installer.exe";
	vDEXTUploadNX_MessageXMLURI = vDEXTUploadNX_Scheme + vDEXTUploadNX_Domain + vDEXTUploadNX_Path + "/NXMessage.xml";
};
function SetNXInstallPageURI(vNewInstallPageURI) { vDEXTUploadNX_InstallPageURI = vNewInstallPageURI; }; function SetNXLocaleXMLFileURL(vLocaleXmlFileURL) { vDEXTUploadNX_MessageXMLURI = vLocaleXmlFileURL; }; function SetNXScriptVersion(vNewScriptVersion) { vDEXTUploadNX_Version = vNewScriptVersion; }; (function (win) { win.NXOperators = {}; win.NXImageViewers = {}; function isIE() { if (navigator.appName == "Microsoft Internet Explorer") return true; else if (navigator.userAgent.indexOf("Trident") >= 0) return true; else return false; }; function isChrome() { if (navigator.userAgent.indexOf("Chrome") >= 0 && navigator.userAgent.indexOf("Safari") >= 0) { return true; } false } function isSafari() { if (navigator.userAgent.indexOf("Chrome") < 0 && navigator.userAgent.indexOf("Safari") >= 0) { return true; } return false; }; function isDefined(obj) { return !(typeof obj === "undefined" || obj === null || obj == ""); }; function checkConfigValues() { if (!isDefined(vDEXTUploadNX_Domain)) return false;if (!isDefined(vDEXTUploadNX_InstallPageURI)) return false;			 if (!isDefined(vDEXTUploadNX_InstallFileURI)) return false; if (!isDefined(vDEXTUploadNX_MessageXMLURI)) return false; if (!isDefined(vNXAuthKey)) return false; return true; }; function getObject(id) { return document.getElementById(id); } win.CreateDEXTUploadNX_Operator = function (config) { if (!isDefined(config)) return; if (!checkConfigValues()) { if(vDEXTUploadNX_InstallPageURI != "") { alert("DEXTUploadNX.js file settings are not correct.\nPlease modify the contents of the file."); return; } } var container = getObject(config.ContainerID); if (!container) { alert("There is no container to position the DEXTUploadNX."); return; } if (!isDefined(config.ElementID)) { alert("DEXTUploadNX Element ID has not been granted."); return; } var style = container.style || {}; var vWidth = 0, vHeight = 0; if ( ("width" in style) && (style.width != "")) { vWidth = style.width; } else { vWidth = "100%"; } if ( ("height" in style) && (style.height != "")) { vHeight = style.height; } else { vHeight = "100%"; } var objectElement = document.createElement("object"); objectElement.setAttribute("width", vWidth); objectElement.setAttribute("height", vHeight); var paramElement = null; paramElement = document.createElement("param"); paramElement.setAttribute("name", "DEXTUploadNX_AuthKey"); paramElement.setAttribute("value", vNXAuthKey); objectElement.appendChild(paramElement); paramElement = document.createElement("param"); paramElement.setAttribute("name", "VersionInScript"); paramElement.setAttribute("value", vDEXTUploadNX_Version); objectElement.appendChild(paramElement); for (var propName in config) { paramElement = document.createElement("param"); paramElement.setAttribute("name", propName); paramElement.setAttribute("value", config[propName]); objectElement.appendChild(paramElement); } paramElement = document.createElement("param"); paramElement.setAttribute("name", "MessageXMLURI"); paramElement.setAttribute("value", vDEXTUploadNX_MessageXMLURI); objectElement.appendChild(paramElement); objectElement.setAttribute("id", config.ElementID); if (isIE()) { objectElement.setAttribute("classid", "CLSID:4BE28987-2231-4680-997B-469C825015E8"); } objectElement.setAttribute("type", "application/dextuploadnx-operator"); objectElement.Config = config; container.appendChild(objectElement); win.NXOperators[objectElement.getAttribute("id")] = objectElement; if(!isDefined(objectElement.NXVersion)) { if(vDEXTUploadNX_InstallPageURI != "") { document.location.href = vDEXTUploadNX_InstallPageURI; return; } } else { var vVerMajor_Script, vVerMinor_Script, vVerBuild_Script, vVerCompile_Script; var vVerMajor_Module, vVerMinor_Module, vVerBuild_Module, vVerCompile_Module; var vVerSplit_Script, vVerSplit_Module; vVerSplit_Script = vDEXTUploadNX_Version.split("."); vVerSplit_Module = objectElement.NXVersion; vVerSplit_Module = vVerSplit_Module.split("."); if( (vVerSplit_Script.length != 4) || (vVerSplit_Module.length != 4)) { if(vDEXTUploadNX_InstallPageURI != "") { document.location.href = vDEXTUploadNX_InstallPageURI; return; } } else { vVerMajor_Script = parseInt(vVerSplit_Script[0], 10); vVerMinor_Script = parseInt(vVerSplit_Script[1], 10); vVerBuild_Script = parseInt(vVerSplit_Script[2], 10); vVerCompile_Script = parseInt(vVerSplit_Script[3], 10); vVerMajor_Module = parseInt(vVerSplit_Module[0], 10); vVerMinor_Module = parseInt(vVerSplit_Module[1], 10); vVerBuild_Module = parseInt(vVerSplit_Module[2], 10); vVerCompile_Module = parseInt(vVerSplit_Module[3], 10); if( (vVerMajor_Module < vVerMajor_Script) || (vVerMinor_Module < vVerMinor_Script) || (vVerBuild_Module < vVerBuild_Script) || (vVerCompile_Module < vVerCompile_Script)) { if(vDEXTUploadNX_InstallPageURI != "") { document.location.href = vDEXTUploadNX_InstallPageURI; return; } } } } if (isIE()) { for (var funcName in objectElement.Config.NXEventFunctions) { if(isDefined(objectElement.attachEvent)) { objectElement.attachEvent(funcName, objectElement.Config.NXEventFunctions[funcName]); } else { var handler = document.createElement("script"); handler.setAttribute("type", "text/javascript"); handler.setAttribute("for", objectElement.Config.ElementID);  var strArgs = objectElement.Config.NXEventFunctions[funcName].toString(); var vBegin = strArgs.indexOf("("); var vEnd = strArgs.indexOf(")"); strArgs = strArgs.substring(vBegin, vEnd+1); handler.event = "" + funcName + strArgs; handler.appendChild(document.createTextNode( 'NXOperators["' + objectElement.Config.ElementID + '"]' + '.Config.NXEventFunctions["' + funcName + '"]' + strArgs )); document.head.appendChild(handler); } } } /* SetLicenseInfo() 메소드가 아닌 object param 으로 인증키를 전달하여 초기화에서 인증키 설정을 한다. 따라서 SetLicenseInfo() 메소드는 더이상 사용되지 않는다. v1.0.1.0 */ }; win.CreateDEXTUploadNX_ImageViewer = function (config) { if (!isDefined(config)) return; if (!checkConfigValues()) { if(vDEXTUploadNX_InstallPageURI != "") { alert("DEXTUploadNX.js file settings are not correct.\nPlease modify the contents of the file."); return; } } var container = getObject(config.ContainerID); if (!container) { alert("There is no container to position the DEXTUploadNX."); return; } if (!isDefined(config.ElementID)) { alert("DEXTUploadNX Element ID has not been granted."); return; } var style = container.style || {}; var vWidth = 0, vHeight = 0; if ( ("width" in style) && (style.width != "")) { vWidth = style.width; } else { vWidth = "100%"; } if ( ("height" in style) && (style.height != "")) { vHeight = style.height; } else { vHeight = "100%"; } var objectElement = document.createElement("object"); objectElement.setAttribute("width", vWidth); objectElement.setAttribute("height", vHeight); var paramElement = null; for (var propName in config) { paramElement = document.createElement("param"); paramElement.setAttribute("name", propName); paramElement.setAttribute("value", config[propName]); objectElement.appendChild(paramElement); } objectElement.setAttribute("id", config.ElementID); if (isIE()) { objectElement.setAttribute("classid", "CLSID:0850DBF1-098C-4D99-B39B-135FA8765158"); } objectElement.setAttribute("type", "application/dextuploadnx-imageviewer"); objectElement.Config = config; container.appendChild(objectElement); win.NXImageViewers[objectElement.getAttribute("id")] = objectElement; if(!isDefined(objectElement.NXVersion)) { if(vDEXTUploadNX_InstallPageURI != "") { document.location.href = vDEXTUploadNX_InstallPageURI; return; } } else { var vVerMajor_Script, vVerMinor_Script, vVerBuild_Script, vVerCompile_Script; var vVerMajor_Module, vVerMinor_Module, vVerBuild_Module, vVerCompile_Module; var vVerSplit_Script, vVerSplit_Module; vVerSplit_Script = vDEXTUploadNX_Version.split("."); vVerSplit_Module = objectElement.NXVersion; vVerSplit_Module = vVerSplit_Module.split("."); if( (vVerSplit_Script.length != 4) || (vVerSplit_Module.length != 4)) { if(vDEXTUploadNX_InstallPageURI != "") { document.location.href = vDEXTUploadNX_InstallPageURI; return; } } else { vVerMajor_Script = parseInt(vVerSplit_Script[0], 10); vVerMinor_Script = parseInt(vVerSplit_Script[1], 10); vVerBuild_Script = parseInt(vVerSplit_Script[2], 10); vVerCompile_Script = parseInt(vVerSplit_Script[3], 10); vVerMajor_Module = parseInt(vVerSplit_Module[0], 10); vVerMinor_Module = parseInt(vVerSplit_Module[1], 10); vVerBuild_Module = parseInt(vVerSplit_Module[2], 10); vVerCompile_Module = parseInt(vVerSplit_Module[3], 10); if( (vVerMajor_Module < vVerMajor_Script) || (vVerMinor_Module < vVerMinor_Script) || (vVerBuild_Module < vVerBuild_Script) || (vVerCompile_Module < vVerCompile_Script)) { if(vDEXTUploadNX_InstallPageURI != "") { document.location.href = vDEXTUploadNX_InstallPageURI; return; } } } } if (isIE()) { for (var funcName in objectElement.Config.NXEventFunctions) { if (isDefined(objectElement.attachEvent)) { objectElement.attachEvent(funcName, objectElement.Config.NXEventFunctions[funcName]); } else { var handler = document.createElement("script"); handler.setAttribute("type", "text/javascript"); handler.setAttribute("for", objectElement.Config.ElementID);  var strArgs = objectElement.Config.NXEventFunctions[funcName].toString(); var vBegin = strArgs.indexOf("("); var vEnd = strArgs.indexOf(")"); strArgs = strArgs.substring(vBegin, vEnd+1); handler.event = "" + funcName + strArgs; handler.appendChild(document.createTextNode( 'NXImageViewers["' + objectElement.Config.ElementID + '"]' + '.Config.NXEventFunctions["' + funcName + '"]' + strArgs )); document.head.appendChild(handler); } } } }; win.NXAlert = function (varElementID, varMsg) { if(arguments.length <= 1) { return; } if(isSafari() || isChrome()) { if(typeof win.NXOperators[varElementID] != "undefined") { win.NXOperators[varElementID].Alert("" + varMsg); } else if (win.NXImageViewers[varElementID] != "undefined"){ win.NXImageViewers[varElementID].Alert("" + varMsg); } } else { alert(varMsg); } }; win.NXConfirm = function (varElementID, varMsg) { var ret; ret = true; if(arguments.length <= 1) { return ret; } if(isSafari() || isChrome()) { if(typeof win.NXOperators[varElementID] != "undefined") { ret = win.NXOperators[varElementID].Confirm("" + varMsg); } else if (win.NXImageViewers[varElementID] != "undefined"){ ret = win.NXImageViewers[varElementID].Confirm("" + varMsg); }  } else { ret = confirm(varMsg); } return ret; }; })(window);
function _getNxURL(vPath) {
	var vRet = "";

	vRet += vDEXTUploadNX_Scheme;
	vRet += vDEXTUploadNX_Domain;
	//vRet += "/응용프로그램 또는 가상디렉터리 명"; // 응용프로그램(or 가상디렉토리) 등록시 사용된 이름
	vRet += vPath; // 응용프로그램(or 가상디렉토리) 등록시 사용된 이름	

	return vRet;
}
