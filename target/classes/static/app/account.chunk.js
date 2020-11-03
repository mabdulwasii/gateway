(window["webpackJsonp"] = window["webpackJsonp"] || []).push([["account"],{

/***/ "./src/main/webapp/app/modules/account/index.tsx":
/*!*******************************************************!*\
  !*** ./src/main/webapp/app/modules/account/index.tsx ***!
  \*******************************************************/
/*! exports provided: default */
/***/ (function(module, __webpack_exports__, __webpack_require__) {

"use strict";
__webpack_require__.r(__webpack_exports__);
/* harmony import */ var react__WEBPACK_IMPORTED_MODULE_0__ = __webpack_require__(/*! react */ "./node_modules/react/index.js");
/* harmony import */ var react__WEBPACK_IMPORTED_MODULE_0___default = /*#__PURE__*/__webpack_require__.n(react__WEBPACK_IMPORTED_MODULE_0__);
/* harmony import */ var app_shared_error_error_boundary_route__WEBPACK_IMPORTED_MODULE_1__ = __webpack_require__(/*! app/shared/error/error-boundary-route */ "./src/main/webapp/app/shared/error/error-boundary-route.tsx");
/* harmony import */ var _settings_settings__WEBPACK_IMPORTED_MODULE_2__ = __webpack_require__(/*! ./settings/settings */ "./src/main/webapp/app/modules/account/settings/settings.tsx");
/* harmony import */ var _password_password__WEBPACK_IMPORTED_MODULE_3__ = __webpack_require__(/*! ./password/password */ "./src/main/webapp/app/modules/account/password/password.tsx");




const Routes = ({ match }) => (react__WEBPACK_IMPORTED_MODULE_0___default.a.createElement("div", null,
    react__WEBPACK_IMPORTED_MODULE_0___default.a.createElement(app_shared_error_error_boundary_route__WEBPACK_IMPORTED_MODULE_1__["default"], { path: `${match.url}/settings`, component: _settings_settings__WEBPACK_IMPORTED_MODULE_2__["default"] }),
    react__WEBPACK_IMPORTED_MODULE_0___default.a.createElement(app_shared_error_error_boundary_route__WEBPACK_IMPORTED_MODULE_1__["default"], { path: `${match.url}/password`, component: _password_password__WEBPACK_IMPORTED_MODULE_3__["default"] })));
/* harmony default export */ __webpack_exports__["default"] = (Routes);

 void function register() { /* react-hot-loader/webpack */ var reactHotLoader = typeof reactHotLoaderGlobal !== 'undefined' ? reactHotLoaderGlobal.default : undefined; if (!reactHotLoader) { return; } /* eslint-disable camelcase, no-undef */ var webpackExports = typeof __webpack_exports__ !== 'undefined' ? __webpack_exports__ : exports; /* eslint-enable camelcase, no-undef */ if (!webpackExports) { return; } if (typeof webpackExports === 'function') { reactHotLoader.register(webpackExports, 'module.exports', "C:\\gateway\\src\\main\\webapp\\app\\modules\\account\\index.tsx"); return; } /* eslint-disable no-restricted-syntax */ for (var key in webpackExports) { /* eslint-enable no-restricted-syntax */ if (!Object.prototype.hasOwnProperty.call(webpackExports, key)) { continue; } var namedExport = void 0; try { namedExport = webpackExports[key]; } catch (err) { continue; } reactHotLoader.register(namedExport, key, "C:\\gateway\\src\\main\\webapp\\app\\modules\\account\\index.tsx"); } }(); 

/***/ }),

/***/ "./src/main/webapp/app/modules/account/password/password.tsx":
/*!*******************************************************************!*\
  !*** ./src/main/webapp/app/modules/account/password/password.tsx ***!
  \*******************************************************************/
/*! exports provided: PasswordPage, default */
/***/ (function(module, __webpack_exports__, __webpack_require__) {

"use strict";
__webpack_require__.r(__webpack_exports__);
/* harmony export (binding) */ __webpack_require__.d(__webpack_exports__, "PasswordPage", function() { return PasswordPage; });
/* harmony import */ var react__WEBPACK_IMPORTED_MODULE_0__ = __webpack_require__(/*! react */ "./node_modules/react/index.js");
/* harmony import */ var react__WEBPACK_IMPORTED_MODULE_0___default = /*#__PURE__*/__webpack_require__.n(react__WEBPACK_IMPORTED_MODULE_0__);
/* harmony import */ var react_jhipster__WEBPACK_IMPORTED_MODULE_1__ = __webpack_require__(/*! react-jhipster */ "./node_modules/react-jhipster/lib/index.js");
/* harmony import */ var react_jhipster__WEBPACK_IMPORTED_MODULE_1___default = /*#__PURE__*/__webpack_require__.n(react_jhipster__WEBPACK_IMPORTED_MODULE_1__);
/* harmony import */ var react_redux__WEBPACK_IMPORTED_MODULE_2__ = __webpack_require__(/*! react-redux */ "./node_modules/react-redux/es/index.js");
/* harmony import */ var availity_reactstrap_validation__WEBPACK_IMPORTED_MODULE_3__ = __webpack_require__(/*! availity-reactstrap-validation */ "./node_modules/availity-reactstrap-validation/lib/index.js");
/* harmony import */ var availity_reactstrap_validation__WEBPACK_IMPORTED_MODULE_3___default = /*#__PURE__*/__webpack_require__.n(availity_reactstrap_validation__WEBPACK_IMPORTED_MODULE_3__);
/* harmony import */ var reactstrap__WEBPACK_IMPORTED_MODULE_4__ = __webpack_require__(/*! reactstrap */ "./node_modules/reactstrap/es/index.js");
/* harmony import */ var app_shared_reducers_authentication__WEBPACK_IMPORTED_MODULE_5__ = __webpack_require__(/*! app/shared/reducers/authentication */ "./src/main/webapp/app/shared/reducers/authentication.ts");
/* harmony import */ var app_shared_layout_password_password_strength_bar__WEBPACK_IMPORTED_MODULE_6__ = __webpack_require__(/*! app/shared/layout/password/password-strength-bar */ "./src/main/webapp/app/shared/layout/password/password-strength-bar.tsx");
/* harmony import */ var _password_reducer__WEBPACK_IMPORTED_MODULE_7__ = __webpack_require__(/*! ./password.reducer */ "./src/main/webapp/app/modules/account/password/password.reducer.ts");








const PasswordPage = (props) => {
    const [password, setPassword] = Object(react__WEBPACK_IMPORTED_MODULE_0__["useState"])('');
    Object(react__WEBPACK_IMPORTED_MODULE_0__["useEffect"])(() => {
        props.reset();
        props.getSession();
        return () => {
            props.reset();
        };
    }, []);
    const handleValidSubmit = (event, values) => {
        props.savePassword(values.currentPassword, values.newPassword);
    };
    const updatePassword = event => setPassword(event.target.value);
    return (react__WEBPACK_IMPORTED_MODULE_0___default.a.createElement("div", null,
        react__WEBPACK_IMPORTED_MODULE_0___default.a.createElement(reactstrap__WEBPACK_IMPORTED_MODULE_4__["Row"], { className: "justify-content-center" },
            react__WEBPACK_IMPORTED_MODULE_0___default.a.createElement(reactstrap__WEBPACK_IMPORTED_MODULE_4__["Col"], { md: "8" },
                react__WEBPACK_IMPORTED_MODULE_0___default.a.createElement("h2", { id: "password-title" },
                    react__WEBPACK_IMPORTED_MODULE_0___default.a.createElement(react_jhipster__WEBPACK_IMPORTED_MODULE_1__["Translate"], { contentKey: "password.title", interpolate: { username: props.account.login } },
                        "Password for ",
                        props.account.login)),
                react__WEBPACK_IMPORTED_MODULE_0___default.a.createElement(availity_reactstrap_validation__WEBPACK_IMPORTED_MODULE_3__["AvForm"], { id: "password-form", onValidSubmit: handleValidSubmit },
                    react__WEBPACK_IMPORTED_MODULE_0___default.a.createElement(availity_reactstrap_validation__WEBPACK_IMPORTED_MODULE_3__["AvField"], { name: "currentPassword", label: Object(react_jhipster__WEBPACK_IMPORTED_MODULE_1__["translate"])('global.form.currentpassword.label'), placeholder: Object(react_jhipster__WEBPACK_IMPORTED_MODULE_1__["translate"])('global.form.currentpassword.placeholder'), type: "password", validate: {
                            required: { value: true, errorMessage: Object(react_jhipster__WEBPACK_IMPORTED_MODULE_1__["translate"])('global.messages.validate.newpassword.required') },
                        } }),
                    react__WEBPACK_IMPORTED_MODULE_0___default.a.createElement(availity_reactstrap_validation__WEBPACK_IMPORTED_MODULE_3__["AvField"], { name: "newPassword", label: Object(react_jhipster__WEBPACK_IMPORTED_MODULE_1__["translate"])('global.form.newpassword.label'), placeholder: Object(react_jhipster__WEBPACK_IMPORTED_MODULE_1__["translate"])('global.form.newpassword.placeholder'), type: "password", validate: {
                            required: { value: true, errorMessage: Object(react_jhipster__WEBPACK_IMPORTED_MODULE_1__["translate"])('global.messages.validate.newpassword.required') },
                            minLength: { value: 4, errorMessage: Object(react_jhipster__WEBPACK_IMPORTED_MODULE_1__["translate"])('global.messages.validate.newpassword.minlength') },
                            maxLength: { value: 50, errorMessage: Object(react_jhipster__WEBPACK_IMPORTED_MODULE_1__["translate"])('global.messages.validate.newpassword.maxlength') },
                        }, onChange: updatePassword }),
                    react__WEBPACK_IMPORTED_MODULE_0___default.a.createElement(app_shared_layout_password_password_strength_bar__WEBPACK_IMPORTED_MODULE_6__["default"], { password: password }),
                    react__WEBPACK_IMPORTED_MODULE_0___default.a.createElement(availity_reactstrap_validation__WEBPACK_IMPORTED_MODULE_3__["AvField"], { name: "confirmPassword", label: Object(react_jhipster__WEBPACK_IMPORTED_MODULE_1__["translate"])('global.form.confirmpassword.label'), placeholder: Object(react_jhipster__WEBPACK_IMPORTED_MODULE_1__["translate"])('global.form.confirmpassword.placeholder'), type: "password", validate: {
                            required: {
                                value: true,
                                errorMessage: Object(react_jhipster__WEBPACK_IMPORTED_MODULE_1__["translate"])('global.messages.validate.confirmpassword.required'),
                            },
                            minLength: {
                                value: 4,
                                errorMessage: Object(react_jhipster__WEBPACK_IMPORTED_MODULE_1__["translate"])('global.messages.validate.confirmpassword.minlength'),
                            },
                            maxLength: {
                                value: 50,
                                errorMessage: Object(react_jhipster__WEBPACK_IMPORTED_MODULE_1__["translate"])('global.messages.validate.confirmpassword.maxlength'),
                            },
                            match: {
                                value: 'newPassword',
                                errorMessage: Object(react_jhipster__WEBPACK_IMPORTED_MODULE_1__["translate"])('global.messages.error.dontmatch'),
                            },
                        } }),
                    react__WEBPACK_IMPORTED_MODULE_0___default.a.createElement(reactstrap__WEBPACK_IMPORTED_MODULE_4__["Button"], { color: "success", type: "submit" },
                        react__WEBPACK_IMPORTED_MODULE_0___default.a.createElement(react_jhipster__WEBPACK_IMPORTED_MODULE_1__["Translate"], { contentKey: "password.form.button" }, "Save")))))));
};
const mapStateToProps = ({ authentication }) => ({
    account: authentication.account,
    isAuthenticated: authentication.isAuthenticated,
});
const mapDispatchToProps = { getSession: app_shared_reducers_authentication__WEBPACK_IMPORTED_MODULE_5__["getSession"], savePassword: _password_reducer__WEBPACK_IMPORTED_MODULE_7__["savePassword"], reset: _password_reducer__WEBPACK_IMPORTED_MODULE_7__["reset"] };
/* harmony default export */ __webpack_exports__["default"] = (Object(react_redux__WEBPACK_IMPORTED_MODULE_2__["connect"])(mapStateToProps, mapDispatchToProps)(PasswordPage));

 void function register() { /* react-hot-loader/webpack */ var reactHotLoader = typeof reactHotLoaderGlobal !== 'undefined' ? reactHotLoaderGlobal.default : undefined; if (!reactHotLoader) { return; } /* eslint-disable camelcase, no-undef */ var webpackExports = typeof __webpack_exports__ !== 'undefined' ? __webpack_exports__ : exports; /* eslint-enable camelcase, no-undef */ if (!webpackExports) { return; } if (typeof webpackExports === 'function') { reactHotLoader.register(webpackExports, 'module.exports', "C:\\gateway\\src\\main\\webapp\\app\\modules\\account\\password\\password.tsx"); return; } /* eslint-disable no-restricted-syntax */ for (var key in webpackExports) { /* eslint-enable no-restricted-syntax */ if (!Object.prototype.hasOwnProperty.call(webpackExports, key)) { continue; } var namedExport = void 0; try { namedExport = webpackExports[key]; } catch (err) { continue; } reactHotLoader.register(namedExport, key, "C:\\gateway\\src\\main\\webapp\\app\\modules\\account\\password\\password.tsx"); } }(); 

/***/ }),

/***/ "./src/main/webapp/app/modules/account/settings/settings.tsx":
/*!*******************************************************************!*\
  !*** ./src/main/webapp/app/modules/account/settings/settings.tsx ***!
  \*******************************************************************/
/*! exports provided: SettingsPage, default */
/***/ (function(module, __webpack_exports__, __webpack_require__) {

"use strict";
__webpack_require__.r(__webpack_exports__);
/* harmony export (binding) */ __webpack_require__.d(__webpack_exports__, "SettingsPage", function() { return SettingsPage; });
/* harmony import */ var react__WEBPACK_IMPORTED_MODULE_0__ = __webpack_require__(/*! react */ "./node_modules/react/index.js");
/* harmony import */ var react__WEBPACK_IMPORTED_MODULE_0___default = /*#__PURE__*/__webpack_require__.n(react__WEBPACK_IMPORTED_MODULE_0__);
/* harmony import */ var reactstrap__WEBPACK_IMPORTED_MODULE_1__ = __webpack_require__(/*! reactstrap */ "./node_modules/reactstrap/es/index.js");
/* harmony import */ var react_redux__WEBPACK_IMPORTED_MODULE_2__ = __webpack_require__(/*! react-redux */ "./node_modules/react-redux/es/index.js");
/* harmony import */ var react_jhipster__WEBPACK_IMPORTED_MODULE_3__ = __webpack_require__(/*! react-jhipster */ "./node_modules/react-jhipster/lib/index.js");
/* harmony import */ var react_jhipster__WEBPACK_IMPORTED_MODULE_3___default = /*#__PURE__*/__webpack_require__.n(react_jhipster__WEBPACK_IMPORTED_MODULE_3__);
/* harmony import */ var availity_reactstrap_validation__WEBPACK_IMPORTED_MODULE_4__ = __webpack_require__(/*! availity-reactstrap-validation */ "./node_modules/availity-reactstrap-validation/lib/index.js");
/* harmony import */ var availity_reactstrap_validation__WEBPACK_IMPORTED_MODULE_4___default = /*#__PURE__*/__webpack_require__.n(availity_reactstrap_validation__WEBPACK_IMPORTED_MODULE_4__);
/* harmony import */ var app_config_translation__WEBPACK_IMPORTED_MODULE_5__ = __webpack_require__(/*! app/config/translation */ "./src/main/webapp/app/config/translation.ts");
/* harmony import */ var app_shared_reducers_authentication__WEBPACK_IMPORTED_MODULE_6__ = __webpack_require__(/*! app/shared/reducers/authentication */ "./src/main/webapp/app/shared/reducers/authentication.ts");
/* harmony import */ var _settings_reducer__WEBPACK_IMPORTED_MODULE_7__ = __webpack_require__(/*! ./settings.reducer */ "./src/main/webapp/app/modules/account/settings/settings.reducer.ts");








const SettingsPage = (props) => {
    Object(react__WEBPACK_IMPORTED_MODULE_0__["useEffect"])(() => {
        props.getSession();
        return () => {
            props.reset();
        };
    }, []);
    const handleValidSubmit = (event, values) => {
        const account = Object.assign(Object.assign({}, props.account), values);
        props.saveAccountSettings(account);
        event.persist();
    };
    return (react__WEBPACK_IMPORTED_MODULE_0___default.a.createElement("div", null,
        react__WEBPACK_IMPORTED_MODULE_0___default.a.createElement(reactstrap__WEBPACK_IMPORTED_MODULE_1__["Row"], { className: "justify-content-center" },
            react__WEBPACK_IMPORTED_MODULE_0___default.a.createElement(reactstrap__WEBPACK_IMPORTED_MODULE_1__["Col"], { md: "8" },
                react__WEBPACK_IMPORTED_MODULE_0___default.a.createElement("h2", { id: "settings-title" },
                    react__WEBPACK_IMPORTED_MODULE_0___default.a.createElement(react_jhipster__WEBPACK_IMPORTED_MODULE_3__["Translate"], { contentKey: "settings.title", interpolate: { username: props.account.login } },
                        "User settings for ",
                        props.account.login)),
                react__WEBPACK_IMPORTED_MODULE_0___default.a.createElement(availity_reactstrap_validation__WEBPACK_IMPORTED_MODULE_4__["AvForm"], { id: "settings-form", onValidSubmit: handleValidSubmit },
                    react__WEBPACK_IMPORTED_MODULE_0___default.a.createElement(availity_reactstrap_validation__WEBPACK_IMPORTED_MODULE_4__["AvField"], { className: "form-control", name: "firstName", label: Object(react_jhipster__WEBPACK_IMPORTED_MODULE_3__["translate"])('settings.form.firstname'), id: "firstName", placeholder: Object(react_jhipster__WEBPACK_IMPORTED_MODULE_3__["translate"])('settings.form.firstname.placeholder'), validate: {
                            required: { value: true, errorMessage: Object(react_jhipster__WEBPACK_IMPORTED_MODULE_3__["translate"])('settings.messages.validate.firstname.required') },
                            minLength: { value: 1, errorMessage: Object(react_jhipster__WEBPACK_IMPORTED_MODULE_3__["translate"])('settings.messages.validate.firstname.minlength') },
                            maxLength: { value: 50, errorMessage: Object(react_jhipster__WEBPACK_IMPORTED_MODULE_3__["translate"])('settings.messages.validate.firstname.maxlength') },
                        }, value: props.account.firstName }),
                    react__WEBPACK_IMPORTED_MODULE_0___default.a.createElement(availity_reactstrap_validation__WEBPACK_IMPORTED_MODULE_4__["AvField"], { className: "form-control", name: "lastName", label: Object(react_jhipster__WEBPACK_IMPORTED_MODULE_3__["translate"])('settings.form.lastname'), id: "lastName", placeholder: Object(react_jhipster__WEBPACK_IMPORTED_MODULE_3__["translate"])('settings.form.lastname.placeholder'), validate: {
                            required: { value: true, errorMessage: Object(react_jhipster__WEBPACK_IMPORTED_MODULE_3__["translate"])('settings.messages.validate.lastname.required') },
                            minLength: { value: 1, errorMessage: Object(react_jhipster__WEBPACK_IMPORTED_MODULE_3__["translate"])('settings.messages.validate.lastname.minlength') },
                            maxLength: { value: 50, errorMessage: Object(react_jhipster__WEBPACK_IMPORTED_MODULE_3__["translate"])('settings.messages.validate.lastname.maxlength') },
                        }, value: props.account.lastName }),
                    react__WEBPACK_IMPORTED_MODULE_0___default.a.createElement(availity_reactstrap_validation__WEBPACK_IMPORTED_MODULE_4__["AvField"], { name: "email", label: Object(react_jhipster__WEBPACK_IMPORTED_MODULE_3__["translate"])('global.form.email.label'), placeholder: Object(react_jhipster__WEBPACK_IMPORTED_MODULE_3__["translate"])('global.form.email.placeholder'), type: "email", validate: {
                            required: { value: true, errorMessage: Object(react_jhipster__WEBPACK_IMPORTED_MODULE_3__["translate"])('global.messages.validate.email.required') },
                            minLength: { value: 5, errorMessage: Object(react_jhipster__WEBPACK_IMPORTED_MODULE_3__["translate"])('global.messages.validate.email.minlength') },
                            maxLength: { value: 254, errorMessage: Object(react_jhipster__WEBPACK_IMPORTED_MODULE_3__["translate"])('global.messages.validate.email.maxlength') },
                        }, value: props.account.email }),
                    react__WEBPACK_IMPORTED_MODULE_0___default.a.createElement(availity_reactstrap_validation__WEBPACK_IMPORTED_MODULE_4__["AvField"], { type: "select", id: "langKey", name: "langKey", className: "form-control", label: Object(react_jhipster__WEBPACK_IMPORTED_MODULE_3__["translate"])('settings.form.language'), value: props.account.langKey }, app_config_translation__WEBPACK_IMPORTED_MODULE_5__["locales"].map(locale => (react__WEBPACK_IMPORTED_MODULE_0___default.a.createElement("option", { value: locale, key: locale }, app_config_translation__WEBPACK_IMPORTED_MODULE_5__["languages"][locale].name)))),
                    react__WEBPACK_IMPORTED_MODULE_0___default.a.createElement(reactstrap__WEBPACK_IMPORTED_MODULE_1__["Button"], { color: "primary", type: "submit" },
                        react__WEBPACK_IMPORTED_MODULE_0___default.a.createElement(react_jhipster__WEBPACK_IMPORTED_MODULE_3__["Translate"], { contentKey: "settings.form.button" }, "Save")))))));
};
const mapStateToProps = ({ authentication }) => ({
    account: authentication.account,
    isAuthenticated: authentication.isAuthenticated,
});
const mapDispatchToProps = { getSession: app_shared_reducers_authentication__WEBPACK_IMPORTED_MODULE_6__["getSession"], saveAccountSettings: _settings_reducer__WEBPACK_IMPORTED_MODULE_7__["saveAccountSettings"], reset: _settings_reducer__WEBPACK_IMPORTED_MODULE_7__["reset"] };
/* harmony default export */ __webpack_exports__["default"] = (Object(react_redux__WEBPACK_IMPORTED_MODULE_2__["connect"])(mapStateToProps, mapDispatchToProps)(SettingsPage));

 void function register() { /* react-hot-loader/webpack */ var reactHotLoader = typeof reactHotLoaderGlobal !== 'undefined' ? reactHotLoaderGlobal.default : undefined; if (!reactHotLoader) { return; } /* eslint-disable camelcase, no-undef */ var webpackExports = typeof __webpack_exports__ !== 'undefined' ? __webpack_exports__ : exports; /* eslint-enable camelcase, no-undef */ if (!webpackExports) { return; } if (typeof webpackExports === 'function') { reactHotLoader.register(webpackExports, 'module.exports', "C:\\gateway\\src\\main\\webapp\\app\\modules\\account\\settings\\settings.tsx"); return; } /* eslint-disable no-restricted-syntax */ for (var key in webpackExports) { /* eslint-enable no-restricted-syntax */ if (!Object.prototype.hasOwnProperty.call(webpackExports, key)) { continue; } var namedExport = void 0; try { namedExport = webpackExports[key]; } catch (err) { continue; } reactHotLoader.register(namedExport, key, "C:\\gateway\\src\\main\\webapp\\app\\modules\\account\\settings\\settings.tsx"); } }(); 

/***/ })

}]);
//# sourceMappingURL=account.chunk.js.map