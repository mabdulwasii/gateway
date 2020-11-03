(window["webpackJsonp"] = window["webpackJsonp"] || []).push([["administration"],{

/***/ "./node_modules/css-loader/dist/cjs.js!./node_modules/postcss-loader/src/index.js!./node_modules/sass-loader/dist/cjs.js?!./src/main/webapp/app/modules/administration/docs/docs.scss":
/*!********************************************************************************************************************************************************************************************!*\
  !*** ./node_modules/css-loader/dist/cjs.js!./node_modules/postcss-loader/src!./node_modules/sass-loader/dist/cjs.js??ref--8-3!./src/main/webapp/app/modules/administration/docs/docs.scss ***!
  \********************************************************************************************************************************************************************************************/
/*! no static exports found */
/***/ (function(module, exports, __webpack_require__) {

// Imports
var ___CSS_LOADER_API_IMPORT___ = __webpack_require__(/*! ../../../../../../../node_modules/css-loader/dist/runtime/api.js */ "./node_modules/css-loader/dist/runtime/api.js");
exports = ___CSS_LOADER_API_IMPORT___(false);
// Module
exports.push([module.i, "iframe {\n  background: white;\n}", ""]);
// Exports
module.exports = exports;


/***/ }),

/***/ "./src/main/webapp/app/modules/administration/audits/audits.tsx":
/*!**********************************************************************!*\
  !*** ./src/main/webapp/app/modules/administration/audits/audits.tsx ***!
  \**********************************************************************/
/*! exports provided: AuditsPage, default */
/***/ (function(module, __webpack_exports__, __webpack_require__) {

"use strict";
__webpack_require__.r(__webpack_exports__);
/* harmony export (binding) */ __webpack_require__.d(__webpack_exports__, "AuditsPage", function() { return AuditsPage; });
/* harmony import */ var react__WEBPACK_IMPORTED_MODULE_0__ = __webpack_require__(/*! react */ "./node_modules/react/index.js");
/* harmony import */ var react__WEBPACK_IMPORTED_MODULE_0___default = /*#__PURE__*/__webpack_require__.n(react__WEBPACK_IMPORTED_MODULE_0__);
/* harmony import */ var react_redux__WEBPACK_IMPORTED_MODULE_1__ = __webpack_require__(/*! react-redux */ "./node_modules/react-redux/es/index.js");
/* harmony import */ var reactstrap__WEBPACK_IMPORTED_MODULE_2__ = __webpack_require__(/*! reactstrap */ "./node_modules/reactstrap/es/index.js");
/* harmony import */ var react_jhipster__WEBPACK_IMPORTED_MODULE_3__ = __webpack_require__(/*! react-jhipster */ "./node_modules/react-jhipster/lib/index.js");
/* harmony import */ var react_jhipster__WEBPACK_IMPORTED_MODULE_3___default = /*#__PURE__*/__webpack_require__.n(react_jhipster__WEBPACK_IMPORTED_MODULE_3__);
/* harmony import */ var _fortawesome_react_fontawesome__WEBPACK_IMPORTED_MODULE_4__ = __webpack_require__(/*! @fortawesome/react-fontawesome */ "./node_modules/@fortawesome/react-fontawesome/index.es.js");
/* harmony import */ var app_config_constants__WEBPACK_IMPORTED_MODULE_5__ = __webpack_require__(/*! app/config/constants */ "./src/main/webapp/app/config/constants.ts");
/* harmony import */ var app_shared_util_pagination_constants__WEBPACK_IMPORTED_MODULE_6__ = __webpack_require__(/*! app/shared/util/pagination.constants */ "./src/main/webapp/app/shared/util/pagination.constants.ts");
/* harmony import */ var app_shared_util_entity_utils__WEBPACK_IMPORTED_MODULE_7__ = __webpack_require__(/*! app/shared/util/entity-utils */ "./src/main/webapp/app/shared/util/entity-utils.ts");
/* harmony import */ var _administration_reducer__WEBPACK_IMPORTED_MODULE_8__ = __webpack_require__(/*! ../administration.reducer */ "./src/main/webapp/app/modules/administration/administration.reducer.ts");









const previousMonth = () => {
    const now = new Date();
    const fromDate = now.getMonth() === 0
        ? new Date(now.getFullYear() - 1, 11, now.getDate())
        : new Date(now.getFullYear(), now.getMonth() - 1, now.getDate());
    return fromDate.toISOString().slice(0, 10);
};
const today = () => {
    // Today + 1 day - needed if the current day must be included
    const day = new Date();
    day.setDate(day.getDate() + 1);
    const toDate = new Date(day.getFullYear(), day.getMonth(), day.getDate());
    return toDate.toISOString().slice(0, 10);
};
const AuditsPage = (props) => {
    const [pagination, setPagination] = Object(react__WEBPACK_IMPORTED_MODULE_0__["useState"])(Object(app_shared_util_entity_utils__WEBPACK_IMPORTED_MODULE_7__["overridePaginationStateWithQueryParams"])(Object(react_jhipster__WEBPACK_IMPORTED_MODULE_3__["getSortState"])(props.location, app_shared_util_pagination_constants__WEBPACK_IMPORTED_MODULE_6__["ITEMS_PER_PAGE"]), props.location.search));
    const [fromDate, setFromDate] = Object(react__WEBPACK_IMPORTED_MODULE_0__["useState"])(previousMonth());
    const [toDate, setToDate] = Object(react__WEBPACK_IMPORTED_MODULE_0__["useState"])(today());
    Object(react__WEBPACK_IMPORTED_MODULE_0__["useEffect"])(() => {
        // eslint-disable-next-line @typescript-eslint/no-use-before-define
        getAllAudits();
    }, [fromDate, toDate, pagination.activePage, pagination.order, pagination.sort]);
    Object(react__WEBPACK_IMPORTED_MODULE_0__["useEffect"])(() => {
        // eslint-disable-next-line @typescript-eslint/no-use-before-define
        transition();
    }, [pagination.activePage, pagination.order, pagination.sort]);
    Object(react__WEBPACK_IMPORTED_MODULE_0__["useEffect"])(() => {
        const params = new URLSearchParams(props.location.search);
        const page = params.get('page');
        const sort = params.get('sort');
        if (page && sort) {
            const sortSplit = sort.split(',');
            setPagination(Object.assign(Object.assign({}, pagination), { activePage: +page, sort: sortSplit[0], order: sortSplit[1] }));
        }
    }, [props.location.search]);
    const onChangeFromDate = evt => setFromDate(evt.target.value);
    const onChangeToDate = evt => setToDate(evt.target.value);
    const sort = p => () => setPagination(Object.assign(Object.assign({}, pagination), { order: pagination.order === 'asc' ? 'desc' : 'asc', sort: p }));
    const transition = () => {
        const endURL = `?page=${pagination.activePage}&sort=${pagination.sort},${pagination.order}`;
        if (props.location.search !== endURL) {
            props.history.push(`${props.location.pathname}${endURL}`);
        }
    };
    const handlePagination = currentPage => setPagination(Object.assign(Object.assign({}, pagination), { activePage: currentPage }));
    const getAllAudits = () => {
        props.getAudits(pagination.activePage - 1, pagination.itemsPerPage, `${pagination.sort},${pagination.order}`, fromDate, toDate);
    };
    const { audits, totalItems } = props;
    return (react__WEBPACK_IMPORTED_MODULE_0___default.a.createElement("div", null,
        react__WEBPACK_IMPORTED_MODULE_0___default.a.createElement("h2", { id: "audits-page-heading" }, "Audits"),
        react__WEBPACK_IMPORTED_MODULE_0___default.a.createElement("span", null,
            react__WEBPACK_IMPORTED_MODULE_0___default.a.createElement(react_jhipster__WEBPACK_IMPORTED_MODULE_3__["Translate"], { contentKey: "audits.filter.from" }, "from")),
        react__WEBPACK_IMPORTED_MODULE_0___default.a.createElement(reactstrap__WEBPACK_IMPORTED_MODULE_2__["Input"], { type: "date", value: fromDate, onChange: onChangeFromDate, name: "fromDate", id: "fromDate" }),
        react__WEBPACK_IMPORTED_MODULE_0___default.a.createElement("span", null,
            react__WEBPACK_IMPORTED_MODULE_0___default.a.createElement(react_jhipster__WEBPACK_IMPORTED_MODULE_3__["Translate"], { contentKey: "audits.filter.to" }, "to")),
        react__WEBPACK_IMPORTED_MODULE_0___default.a.createElement(reactstrap__WEBPACK_IMPORTED_MODULE_2__["Input"], { type: "date", value: toDate, onChange: onChangeToDate, name: "toDate", id: "toDate" }),
        audits && audits.length > 0 ? (react__WEBPACK_IMPORTED_MODULE_0___default.a.createElement(reactstrap__WEBPACK_IMPORTED_MODULE_2__["Table"], { striped: true, responsive: true },
            react__WEBPACK_IMPORTED_MODULE_0___default.a.createElement("thead", null,
                react__WEBPACK_IMPORTED_MODULE_0___default.a.createElement("tr", null,
                    react__WEBPACK_IMPORTED_MODULE_0___default.a.createElement("th", { onClick: sort('auditEventDate') },
                        react__WEBPACK_IMPORTED_MODULE_0___default.a.createElement(react_jhipster__WEBPACK_IMPORTED_MODULE_3__["Translate"], { contentKey: "audits.table.header.date" }, "Date"),
                        react__WEBPACK_IMPORTED_MODULE_0___default.a.createElement(_fortawesome_react_fontawesome__WEBPACK_IMPORTED_MODULE_4__["FontAwesomeIcon"], { icon: "sort" })),
                    react__WEBPACK_IMPORTED_MODULE_0___default.a.createElement("th", { onClick: sort('principal') },
                        react__WEBPACK_IMPORTED_MODULE_0___default.a.createElement(react_jhipster__WEBPACK_IMPORTED_MODULE_3__["Translate"], { contentKey: "audits.table.header.principal" }, "User"),
                        react__WEBPACK_IMPORTED_MODULE_0___default.a.createElement(_fortawesome_react_fontawesome__WEBPACK_IMPORTED_MODULE_4__["FontAwesomeIcon"], { icon: "sort" })),
                    react__WEBPACK_IMPORTED_MODULE_0___default.a.createElement("th", { onClick: sort('auditEventType') },
                        react__WEBPACK_IMPORTED_MODULE_0___default.a.createElement(react_jhipster__WEBPACK_IMPORTED_MODULE_3__["Translate"], { contentKey: "audits.table.header.status" }, "State"),
                        react__WEBPACK_IMPORTED_MODULE_0___default.a.createElement(_fortawesome_react_fontawesome__WEBPACK_IMPORTED_MODULE_4__["FontAwesomeIcon"], { icon: "sort" })),
                    react__WEBPACK_IMPORTED_MODULE_0___default.a.createElement("th", null,
                        react__WEBPACK_IMPORTED_MODULE_0___default.a.createElement(react_jhipster__WEBPACK_IMPORTED_MODULE_3__["Translate"], { contentKey: "audits.table.header.data" }, "Extra data")))),
            react__WEBPACK_IMPORTED_MODULE_0___default.a.createElement("tbody", null, audits.map((audit, i) => (react__WEBPACK_IMPORTED_MODULE_0___default.a.createElement("tr", { key: `audit-${i}` },
                react__WEBPACK_IMPORTED_MODULE_0___default.a.createElement("td", null, react__WEBPACK_IMPORTED_MODULE_0___default.a.createElement(react_jhipster__WEBPACK_IMPORTED_MODULE_3__["TextFormat"], { value: audit.timestamp, type: "date", format: app_config_constants__WEBPACK_IMPORTED_MODULE_5__["APP_TIMESTAMP_FORMAT"] })),
                react__WEBPACK_IMPORTED_MODULE_0___default.a.createElement("td", null, audit.principal),
                react__WEBPACK_IMPORTED_MODULE_0___default.a.createElement("td", null, audit.type),
                react__WEBPACK_IMPORTED_MODULE_0___default.a.createElement("td", null,
                    audit.data ? audit.data.message : null,
                    audit.data ? audit.data.remoteAddress : null))))))) : (react__WEBPACK_IMPORTED_MODULE_0___default.a.createElement("div", { className: "alert alert-warning" },
            react__WEBPACK_IMPORTED_MODULE_0___default.a.createElement(react_jhipster__WEBPACK_IMPORTED_MODULE_3__["Translate"], { contentKey: "audits.notFound" }, "No audit found"))),
        props.totalItems ? (react__WEBPACK_IMPORTED_MODULE_0___default.a.createElement("div", { className: audits && audits.length > 0 ? '' : 'd-none' },
            react__WEBPACK_IMPORTED_MODULE_0___default.a.createElement(reactstrap__WEBPACK_IMPORTED_MODULE_2__["Row"], { className: "justify-content-center" },
                react__WEBPACK_IMPORTED_MODULE_0___default.a.createElement(react_jhipster__WEBPACK_IMPORTED_MODULE_3__["JhiItemCount"], { page: pagination.activePage, total: totalItems, itemsPerPage: pagination.itemsPerPage, i18nEnabled: true })),
            react__WEBPACK_IMPORTED_MODULE_0___default.a.createElement(reactstrap__WEBPACK_IMPORTED_MODULE_2__["Row"], { className: "justify-content-center" },
                react__WEBPACK_IMPORTED_MODULE_0___default.a.createElement(react_jhipster__WEBPACK_IMPORTED_MODULE_3__["JhiPagination"], { activePage: pagination.activePage, onSelect: handlePagination, maxButtons: 5, itemsPerPage: pagination.itemsPerPage, totalItems: props.totalItems })))) : ('')));
};
const mapStateToProps = (storeState) => ({
    audits: storeState.administration.audits,
    totalItems: storeState.administration.totalItems,
});
const mapDispatchToProps = { getAudits: _administration_reducer__WEBPACK_IMPORTED_MODULE_8__["getAudits"] };
/* harmony default export */ __webpack_exports__["default"] = (Object(react_redux__WEBPACK_IMPORTED_MODULE_1__["connect"])(mapStateToProps, mapDispatchToProps)(AuditsPage));

 void function register() { /* react-hot-loader/webpack */ var reactHotLoader = typeof reactHotLoaderGlobal !== 'undefined' ? reactHotLoaderGlobal.default : undefined; if (!reactHotLoader) { return; } /* eslint-disable camelcase, no-undef */ var webpackExports = typeof __webpack_exports__ !== 'undefined' ? __webpack_exports__ : exports; /* eslint-enable camelcase, no-undef */ if (!webpackExports) { return; } if (typeof webpackExports === 'function') { reactHotLoader.register(webpackExports, 'module.exports', "C:\\gateway\\src\\main\\webapp\\app\\modules\\administration\\audits\\audits.tsx"); return; } /* eslint-disable no-restricted-syntax */ for (var key in webpackExports) { /* eslint-enable no-restricted-syntax */ if (!Object.prototype.hasOwnProperty.call(webpackExports, key)) { continue; } var namedExport = void 0; try { namedExport = webpackExports[key]; } catch (err) { continue; } reactHotLoader.register(namedExport, key, "C:\\gateway\\src\\main\\webapp\\app\\modules\\administration\\audits\\audits.tsx"); } }(); 

/***/ }),

/***/ "./src/main/webapp/app/modules/administration/configuration/configuration.tsx":
/*!************************************************************************************!*\
  !*** ./src/main/webapp/app/modules/administration/configuration/configuration.tsx ***!
  \************************************************************************************/
/*! exports provided: ConfigurationPage, default */
/***/ (function(module, __webpack_exports__, __webpack_require__) {

"use strict";
__webpack_require__.r(__webpack_exports__);
/* harmony export (binding) */ __webpack_require__.d(__webpack_exports__, "ConfigurationPage", function() { return ConfigurationPage; });
/* harmony import */ var react__WEBPACK_IMPORTED_MODULE_0__ = __webpack_require__(/*! react */ "./node_modules/react/index.js");
/* harmony import */ var react__WEBPACK_IMPORTED_MODULE_0___default = /*#__PURE__*/__webpack_require__.n(react__WEBPACK_IMPORTED_MODULE_0__);
/* harmony import */ var react_redux__WEBPACK_IMPORTED_MODULE_1__ = __webpack_require__(/*! react-redux */ "./node_modules/react-redux/es/index.js");
/* harmony import */ var reactstrap__WEBPACK_IMPORTED_MODULE_2__ = __webpack_require__(/*! reactstrap */ "./node_modules/reactstrap/es/index.js");
/* harmony import */ var react_jhipster__WEBPACK_IMPORTED_MODULE_3__ = __webpack_require__(/*! react-jhipster */ "./node_modules/react-jhipster/lib/index.js");
/* harmony import */ var react_jhipster__WEBPACK_IMPORTED_MODULE_3___default = /*#__PURE__*/__webpack_require__.n(react_jhipster__WEBPACK_IMPORTED_MODULE_3__);
/* harmony import */ var _administration_reducer__WEBPACK_IMPORTED_MODULE_4__ = __webpack_require__(/*! ../administration.reducer */ "./src/main/webapp/app/modules/administration/administration.reducer.ts");





const ConfigurationPage = (props) => {
    const [filter, setFilter] = Object(react__WEBPACK_IMPORTED_MODULE_0__["useState"])('');
    const [reversePrefix, setReversePrefix] = Object(react__WEBPACK_IMPORTED_MODULE_0__["useState"])(false);
    const [reverseProperties, setReverseProperties] = Object(react__WEBPACK_IMPORTED_MODULE_0__["useState"])(false);
    Object(react__WEBPACK_IMPORTED_MODULE_0__["useEffect"])(() => {
        props.getConfigurations();
        props.getEnv();
    }, []);
    const changeFilter = evt => setFilter(evt.target.value);
    const envFilterFn = configProp => configProp.toUpperCase().includes(filter.toUpperCase());
    const propsFilterFn = configProp => configProp.prefix.toUpperCase().includes(filter.toUpperCase());
    const changeReversePrefix = () => setReversePrefix(!reversePrefix);
    const changeReverseProperties = () => setReverseProperties(!reverseProperties);
    const getContextList = contexts => Object.values(contexts)
        .map((v) => v.beans)
        .reduce((acc, e) => (Object.assign(Object.assign({}, acc), e)));
    const { configuration } = props;
    const configProps = configuration && configuration.configProps ? configuration.configProps : {};
    const env = configuration && configuration.env ? configuration.env : {};
    return (react__WEBPACK_IMPORTED_MODULE_0___default.a.createElement("div", null,
        react__WEBPACK_IMPORTED_MODULE_0___default.a.createElement("h2", { id: "configuration-page-heading" },
            react__WEBPACK_IMPORTED_MODULE_0___default.a.createElement(react_jhipster__WEBPACK_IMPORTED_MODULE_3__["Translate"], { contentKey: "configuration.title" }, "Configuration")),
        react__WEBPACK_IMPORTED_MODULE_0___default.a.createElement("span", null,
            react__WEBPACK_IMPORTED_MODULE_0___default.a.createElement(react_jhipster__WEBPACK_IMPORTED_MODULE_3__["Translate"], { contentKey: "configuration.filter" }, "Filter")),
        ' ',
        react__WEBPACK_IMPORTED_MODULE_0___default.a.createElement(reactstrap__WEBPACK_IMPORTED_MODULE_2__["Input"], { type: "search", value: filter, onChange: changeFilter, name: "search", id: "search" }),
        react__WEBPACK_IMPORTED_MODULE_0___default.a.createElement("label", null, "Spring configuration"),
        react__WEBPACK_IMPORTED_MODULE_0___default.a.createElement(reactstrap__WEBPACK_IMPORTED_MODULE_2__["Table"], { className: "table table-striped table-bordered table-responsive d-table" },
            react__WEBPACK_IMPORTED_MODULE_0___default.a.createElement("thead", null,
                react__WEBPACK_IMPORTED_MODULE_0___default.a.createElement("tr", null,
                    react__WEBPACK_IMPORTED_MODULE_0___default.a.createElement("th", { onClick: changeReversePrefix },
                        react__WEBPACK_IMPORTED_MODULE_0___default.a.createElement(react_jhipster__WEBPACK_IMPORTED_MODULE_3__["Translate"], { contentKey: "configuration.table.prefix" }, "Prefix")),
                    react__WEBPACK_IMPORTED_MODULE_0___default.a.createElement("th", { onClick: changeReverseProperties },
                        react__WEBPACK_IMPORTED_MODULE_0___default.a.createElement(react_jhipster__WEBPACK_IMPORTED_MODULE_3__["Translate"], { contentKey: "configuration.table.properties" }, "Properties")))),
            react__WEBPACK_IMPORTED_MODULE_0___default.a.createElement("tbody", null, configProps.contexts
                ? Object.values(getContextList(configProps.contexts))
                    .filter(propsFilterFn)
                    .map((property, propIndex) => (react__WEBPACK_IMPORTED_MODULE_0___default.a.createElement("tr", { key: propIndex },
                    react__WEBPACK_IMPORTED_MODULE_0___default.a.createElement("td", null, property['prefix']),
                    react__WEBPACK_IMPORTED_MODULE_0___default.a.createElement("td", null, Object.keys(property['properties']).map((propKey, index) => (react__WEBPACK_IMPORTED_MODULE_0___default.a.createElement(reactstrap__WEBPACK_IMPORTED_MODULE_2__["Row"], { key: index },
                        react__WEBPACK_IMPORTED_MODULE_0___default.a.createElement(reactstrap__WEBPACK_IMPORTED_MODULE_2__["Col"], { md: "4" }, propKey),
                        react__WEBPACK_IMPORTED_MODULE_0___default.a.createElement(reactstrap__WEBPACK_IMPORTED_MODULE_2__["Col"], { md: "8" },
                            react__WEBPACK_IMPORTED_MODULE_0___default.a.createElement(reactstrap__WEBPACK_IMPORTED_MODULE_2__["Badge"], { className: "float-right badge-secondary break" }, JSON.stringify(property['properties'][propKey]))))))))))
                : null)),
        env.propertySources
            ? env.propertySources.map((envKey, envIndex) => (react__WEBPACK_IMPORTED_MODULE_0___default.a.createElement("div", { key: envIndex },
                react__WEBPACK_IMPORTED_MODULE_0___default.a.createElement("h4", null,
                    react__WEBPACK_IMPORTED_MODULE_0___default.a.createElement("span", null, envKey.name)),
                react__WEBPACK_IMPORTED_MODULE_0___default.a.createElement(reactstrap__WEBPACK_IMPORTED_MODULE_2__["Table"], { className: "table table-sm table-striped table-bordered table-responsive d-table" },
                    react__WEBPACK_IMPORTED_MODULE_0___default.a.createElement("thead", null,
                        react__WEBPACK_IMPORTED_MODULE_0___default.a.createElement("tr", { key: envIndex },
                            react__WEBPACK_IMPORTED_MODULE_0___default.a.createElement("th", { className: "w-40" }, "Property"),
                            react__WEBPACK_IMPORTED_MODULE_0___default.a.createElement("th", { className: "w-60" }, "Value"))),
                    react__WEBPACK_IMPORTED_MODULE_0___default.a.createElement("tbody", null, Object.keys(envKey.properties)
                        .filter(envFilterFn)
                        .map((propKey, propIndex) => (react__WEBPACK_IMPORTED_MODULE_0___default.a.createElement("tr", { key: propIndex },
                        react__WEBPACK_IMPORTED_MODULE_0___default.a.createElement("td", { className: "break" }, propKey),
                        react__WEBPACK_IMPORTED_MODULE_0___default.a.createElement("td", { className: "break" },
                            react__WEBPACK_IMPORTED_MODULE_0___default.a.createElement("span", { className: "float-right badge badge-secondary break" }, envKey.properties[propKey].value))))))))))
            : null));
};
const mapStateToProps = ({ administration }) => ({
    configuration: administration.configuration,
    isFetching: administration.loading,
});
const mapDispatchToProps = { getConfigurations: _administration_reducer__WEBPACK_IMPORTED_MODULE_4__["getConfigurations"], getEnv: _administration_reducer__WEBPACK_IMPORTED_MODULE_4__["getEnv"] };
/* harmony default export */ __webpack_exports__["default"] = (Object(react_redux__WEBPACK_IMPORTED_MODULE_1__["connect"])(mapStateToProps, mapDispatchToProps)(ConfigurationPage));

 void function register() { /* react-hot-loader/webpack */ var reactHotLoader = typeof reactHotLoaderGlobal !== 'undefined' ? reactHotLoaderGlobal.default : undefined; if (!reactHotLoader) { return; } /* eslint-disable camelcase, no-undef */ var webpackExports = typeof __webpack_exports__ !== 'undefined' ? __webpack_exports__ : exports; /* eslint-enable camelcase, no-undef */ if (!webpackExports) { return; } if (typeof webpackExports === 'function') { reactHotLoader.register(webpackExports, 'module.exports', "C:\\gateway\\src\\main\\webapp\\app\\modules\\administration\\configuration\\configuration.tsx"); return; } /* eslint-disable no-restricted-syntax */ for (var key in webpackExports) { /* eslint-enable no-restricted-syntax */ if (!Object.prototype.hasOwnProperty.call(webpackExports, key)) { continue; } var namedExport = void 0; try { namedExport = webpackExports[key]; } catch (err) { continue; } reactHotLoader.register(namedExport, key, "C:\\gateway\\src\\main\\webapp\\app\\modules\\administration\\configuration\\configuration.tsx"); } }(); 

/***/ }),

/***/ "./src/main/webapp/app/modules/administration/docs/docs.scss":
/*!*******************************************************************!*\
  !*** ./src/main/webapp/app/modules/administration/docs/docs.scss ***!
  \*******************************************************************/
/*! no static exports found */
/***/ (function(module, exports, __webpack_require__) {

var api = __webpack_require__(/*! ../../../../../../../node_modules/style-loader/dist/runtime/injectStylesIntoStyleTag.js */ "./node_modules/style-loader/dist/runtime/injectStylesIntoStyleTag.js");
            var content = __webpack_require__(/*! !../../../../../../../node_modules/css-loader/dist/cjs.js!../../../../../../../node_modules/postcss-loader/src!../../../../../../../node_modules/sass-loader/dist/cjs.js??ref--8-3!./docs.scss */ "./node_modules/css-loader/dist/cjs.js!./node_modules/postcss-loader/src/index.js!./node_modules/sass-loader/dist/cjs.js?!./src/main/webapp/app/modules/administration/docs/docs.scss");

            content = content.__esModule ? content.default : content;

            if (typeof content === 'string') {
              content = [[module.i, content, '']];
            }

var options = {};

options.insert = "head";
options.singleton = false;

var update = api(content, options);


if (true) {
  if (!content.locals || module.hot.invalidate) {
    var isEqualLocals = function isEqualLocals(a, b) {
  if (!a && b || a && !b) {
    return false;
  }

  var p;

  for (p in a) {
    if (a[p] !== b[p]) {
      return false;
    }
  }

  for (p in b) {
    if (!a[p]) {
      return false;
    }
  }

  return true;
};
    var oldLocals = content.locals;

    module.hot.accept(
      /*! !../../../../../../../node_modules/css-loader/dist/cjs.js!../../../../../../../node_modules/postcss-loader/src!../../../../../../../node_modules/sass-loader/dist/cjs.js??ref--8-3!./docs.scss */ "./node_modules/css-loader/dist/cjs.js!./node_modules/postcss-loader/src/index.js!./node_modules/sass-loader/dist/cjs.js?!./src/main/webapp/app/modules/administration/docs/docs.scss",
      function () {
        var newContent = __webpack_require__(/*! !../../../../../../../node_modules/css-loader/dist/cjs.js!../../../../../../../node_modules/postcss-loader/src!../../../../../../../node_modules/sass-loader/dist/cjs.js??ref--8-3!./docs.scss */ "./node_modules/css-loader/dist/cjs.js!./node_modules/postcss-loader/src/index.js!./node_modules/sass-loader/dist/cjs.js?!./src/main/webapp/app/modules/administration/docs/docs.scss");

              newContent = newContent.__esModule ? newContent.default : newContent;

              if (typeof newContent === 'string') {
                newContent = [[module.i, newContent, '']];
              }

              if (!isEqualLocals(oldLocals, newContent.locals)) {
                module.hot.invalidate();

                return;
              }

              oldLocals = newContent.locals;

              update(newContent);
      }
    )
  }

  module.hot.dispose(function() {
    update();
  });
}

module.exports = content.locals || {};

/***/ }),

/***/ "./src/main/webapp/app/modules/administration/docs/docs.tsx":
/*!******************************************************************!*\
  !*** ./src/main/webapp/app/modules/administration/docs/docs.tsx ***!
  \******************************************************************/
/*! exports provided: default */
/***/ (function(module, __webpack_exports__, __webpack_require__) {

"use strict";
__webpack_require__.r(__webpack_exports__);
/* harmony import */ var _docs_scss__WEBPACK_IMPORTED_MODULE_0__ = __webpack_require__(/*! ./docs.scss */ "./src/main/webapp/app/modules/administration/docs/docs.scss");
/* harmony import */ var _docs_scss__WEBPACK_IMPORTED_MODULE_0___default = /*#__PURE__*/__webpack_require__.n(_docs_scss__WEBPACK_IMPORTED_MODULE_0__);
/* harmony import */ var react__WEBPACK_IMPORTED_MODULE_1__ = __webpack_require__(/*! react */ "./node_modules/react/index.js");
/* harmony import */ var react__WEBPACK_IMPORTED_MODULE_1___default = /*#__PURE__*/__webpack_require__.n(react__WEBPACK_IMPORTED_MODULE_1__);


const DocsPage = () => (react__WEBPACK_IMPORTED_MODULE_1___default.a.createElement("div", null,
    react__WEBPACK_IMPORTED_MODULE_1___default.a.createElement("iframe", { src: "../swagger-ui/index.html", width: "100%", height: "800", title: "Swagger UI", seamless: true, style: { border: 'none' } })));
/* harmony default export */ __webpack_exports__["default"] = (DocsPage);

 void function register() { /* react-hot-loader/webpack */ var reactHotLoader = typeof reactHotLoaderGlobal !== 'undefined' ? reactHotLoaderGlobal.default : undefined; if (!reactHotLoader) { return; } /* eslint-disable camelcase, no-undef */ var webpackExports = typeof __webpack_exports__ !== 'undefined' ? __webpack_exports__ : exports; /* eslint-enable camelcase, no-undef */ if (!webpackExports) { return; } if (typeof webpackExports === 'function') { reactHotLoader.register(webpackExports, 'module.exports', "C:\\gateway\\src\\main\\webapp\\app\\modules\\administration\\docs\\docs.tsx"); return; } /* eslint-disable no-restricted-syntax */ for (var key in webpackExports) { /* eslint-enable no-restricted-syntax */ if (!Object.prototype.hasOwnProperty.call(webpackExports, key)) { continue; } var namedExport = void 0; try { namedExport = webpackExports[key]; } catch (err) { continue; } reactHotLoader.register(namedExport, key, "C:\\gateway\\src\\main\\webapp\\app\\modules\\administration\\docs\\docs.tsx"); } }(); 

/***/ }),

/***/ "./src/main/webapp/app/modules/administration/gateway/gateway.tsx":
/*!************************************************************************!*\
  !*** ./src/main/webapp/app/modules/administration/gateway/gateway.tsx ***!
  \************************************************************************/
/*! exports provided: GatewayPage, default */
/***/ (function(module, __webpack_exports__, __webpack_require__) {

"use strict";
__webpack_require__.r(__webpack_exports__);
/* harmony export (binding) */ __webpack_require__.d(__webpack_exports__, "GatewayPage", function() { return GatewayPage; });
/* harmony import */ var react__WEBPACK_IMPORTED_MODULE_0__ = __webpack_require__(/*! react */ "./node_modules/react/index.js");
/* harmony import */ var react__WEBPACK_IMPORTED_MODULE_0___default = /*#__PURE__*/__webpack_require__.n(react__WEBPACK_IMPORTED_MODULE_0__);
/* harmony import */ var react_redux__WEBPACK_IMPORTED_MODULE_1__ = __webpack_require__(/*! react-redux */ "./node_modules/react-redux/es/index.js");
/* harmony import */ var react_jhipster__WEBPACK_IMPORTED_MODULE_2__ = __webpack_require__(/*! react-jhipster */ "./node_modules/react-jhipster/lib/index.js");
/* harmony import */ var react_jhipster__WEBPACK_IMPORTED_MODULE_2___default = /*#__PURE__*/__webpack_require__.n(react_jhipster__WEBPACK_IMPORTED_MODULE_2__);
/* harmony import */ var _fortawesome_react_fontawesome__WEBPACK_IMPORTED_MODULE_3__ = __webpack_require__(/*! @fortawesome/react-fontawesome */ "./node_modules/@fortawesome/react-fontawesome/index.es.js");
/* harmony import */ var reactstrap__WEBPACK_IMPORTED_MODULE_4__ = __webpack_require__(/*! reactstrap */ "./node_modules/reactstrap/es/index.js");
/* harmony import */ var _administration_reducer__WEBPACK_IMPORTED_MODULE_5__ = __webpack_require__(/*! ../administration.reducer */ "./src/main/webapp/app/modules/administration/administration.reducer.ts");






class GatewayPage extends react__WEBPACK_IMPORTED_MODULE_0___default.a.Component {
    constructor() {
        super(...arguments);
        this.metadata = instance => {
            const spans = [];
            Object.keys(instance).map((key, index) => {
                spans.push(react__WEBPACK_IMPORTED_MODULE_0___default.a.createElement("span", { key: key.toString() + 'value' },
                    react__WEBPACK_IMPORTED_MODULE_0___default.a.createElement(reactstrap__WEBPACK_IMPORTED_MODULE_4__["Badge"], { key: key.toString() + '-containerbadge', className: "font-weight-normal" },
                        react__WEBPACK_IMPORTED_MODULE_0___default.a.createElement(reactstrap__WEBPACK_IMPORTED_MODULE_4__["Badge"], { key: key.toString() + '-badge', color: "info", className: "font-weight-normal", pill: true }, key),
                        instance[key])));
            });
            return spans;
        };
        this.badgeInfo = info => {
            if (info) {
                if (info.status === 'UP') {
                    return react__WEBPACK_IMPORTED_MODULE_0___default.a.createElement(reactstrap__WEBPACK_IMPORTED_MODULE_4__["Badge"], { color: "success" }, info.status);
                }
                else {
                    return react__WEBPACK_IMPORTED_MODULE_0___default.a.createElement(reactstrap__WEBPACK_IMPORTED_MODULE_4__["Badge"], { color: "danger" }, info.status);
                }
            }
            else {
                return react__WEBPACK_IMPORTED_MODULE_0___default.a.createElement(reactstrap__WEBPACK_IMPORTED_MODULE_4__["Badge"], { color: "warning" }, "?");
            }
        };
        this.instanceInfo = route => {
            if (route) {
                return (react__WEBPACK_IMPORTED_MODULE_0___default.a.createElement(reactstrap__WEBPACK_IMPORTED_MODULE_4__["Table"], { striped: true, responsive: true },
                    react__WEBPACK_IMPORTED_MODULE_0___default.a.createElement("tbody", null, route.serviceInstances.map((instance, i) => (react__WEBPACK_IMPORTED_MODULE_0___default.a.createElement("tr", { key: instance.instanceInfo + '-info' },
                        react__WEBPACK_IMPORTED_MODULE_0___default.a.createElement("td", null,
                            react__WEBPACK_IMPORTED_MODULE_0___default.a.createElement("a", { href: instance.uri, target: "_blank", rel: "noopener noreferrer" }, instance.uri)),
                        react__WEBPACK_IMPORTED_MODULE_0___default.a.createElement("td", null, this.badgeInfo(instance.instanceInfo)),
                        react__WEBPACK_IMPORTED_MODULE_0___default.a.createElement("td", null, this.metadata(instance.metadata))))))));
            }
        };
        this.gatewayRoutes = () => {
            if (!this.props.isFetching) {
                this.props.gatewayRoutes();
            }
        };
    }
    componentDidMount() {
        this.props.gatewayRoutes();
    }
    render() {
        const { routes, isFetching } = this.props;
        return (react__WEBPACK_IMPORTED_MODULE_0___default.a.createElement("div", null,
            react__WEBPACK_IMPORTED_MODULE_0___default.a.createElement("h2", null, "Gateway"),
            react__WEBPACK_IMPORTED_MODULE_0___default.a.createElement("p", null,
                react__WEBPACK_IMPORTED_MODULE_0___default.a.createElement(reactstrap__WEBPACK_IMPORTED_MODULE_4__["Button"], { onClick: this.gatewayRoutes, color: isFetching ? 'danger' : 'primary', disabled: isFetching },
                    react__WEBPACK_IMPORTED_MODULE_0___default.a.createElement(_fortawesome_react_fontawesome__WEBPACK_IMPORTED_MODULE_3__["FontAwesomeIcon"], { icon: "sync" }),
                    "\u00A0",
                    react__WEBPACK_IMPORTED_MODULE_0___default.a.createElement(react_jhipster__WEBPACK_IMPORTED_MODULE_2__["Translate"], { component: "span", contentKey: "health.refresh.button" }, "Refresh"))),
            react__WEBPACK_IMPORTED_MODULE_0___default.a.createElement(reactstrap__WEBPACK_IMPORTED_MODULE_4__["Table"], { striped: true, responsive: true },
                react__WEBPACK_IMPORTED_MODULE_0___default.a.createElement("thead", null,
                    react__WEBPACK_IMPORTED_MODULE_0___default.a.createElement("tr", { key: "header" },
                        react__WEBPACK_IMPORTED_MODULE_0___default.a.createElement("th", null,
                            react__WEBPACK_IMPORTED_MODULE_0___default.a.createElement(react_jhipster__WEBPACK_IMPORTED_MODULE_2__["Translate"], { contentKey: "gateway.routes.url" }, "URL")),
                        react__WEBPACK_IMPORTED_MODULE_0___default.a.createElement("th", null,
                            react__WEBPACK_IMPORTED_MODULE_0___default.a.createElement(react_jhipster__WEBPACK_IMPORTED_MODULE_2__["Translate"], { contentKey: "gateway.routes.service" }, "Service")),
                        react__WEBPACK_IMPORTED_MODULE_0___default.a.createElement("th", null,
                            react__WEBPACK_IMPORTED_MODULE_0___default.a.createElement(react_jhipster__WEBPACK_IMPORTED_MODULE_2__["Translate"], { contentKey: "gateway.routes.servers" }, "Available servers")))),
                react__WEBPACK_IMPORTED_MODULE_0___default.a.createElement("tbody", null, routes.map((route, i) => (react__WEBPACK_IMPORTED_MODULE_0___default.a.createElement("tr", { key: `routes-${i}` },
                    react__WEBPACK_IMPORTED_MODULE_0___default.a.createElement("td", null, route.path),
                    react__WEBPACK_IMPORTED_MODULE_0___default.a.createElement("td", null, route.serviceId),
                    react__WEBPACK_IMPORTED_MODULE_0___default.a.createElement("td", null, this.instanceInfo(route)))))))));
    }
}
const mapStateToProps = storeState => ({
    routes: storeState.administration.gateway.routes,
    account: storeState.authentication.account,
    isFetching: storeState.administration.loading,
});
const mapDispatchToProps = { gatewayRoutes: _administration_reducer__WEBPACK_IMPORTED_MODULE_5__["gatewayRoutes"] };
/* harmony default export */ __webpack_exports__["default"] = (Object(react_redux__WEBPACK_IMPORTED_MODULE_1__["connect"])(mapStateToProps, mapDispatchToProps)(GatewayPage));

 void function register() { /* react-hot-loader/webpack */ var reactHotLoader = typeof reactHotLoaderGlobal !== 'undefined' ? reactHotLoaderGlobal.default : undefined; if (!reactHotLoader) { return; } /* eslint-disable camelcase, no-undef */ var webpackExports = typeof __webpack_exports__ !== 'undefined' ? __webpack_exports__ : exports; /* eslint-enable camelcase, no-undef */ if (!webpackExports) { return; } if (typeof webpackExports === 'function') { reactHotLoader.register(webpackExports, 'module.exports', "C:\\gateway\\src\\main\\webapp\\app\\modules\\administration\\gateway\\gateway.tsx"); return; } /* eslint-disable no-restricted-syntax */ for (var key in webpackExports) { /* eslint-enable no-restricted-syntax */ if (!Object.prototype.hasOwnProperty.call(webpackExports, key)) { continue; } var namedExport = void 0; try { namedExport = webpackExports[key]; } catch (err) { continue; } reactHotLoader.register(namedExport, key, "C:\\gateway\\src\\main\\webapp\\app\\modules\\administration\\gateway\\gateway.tsx"); } }(); 

/***/ }),

/***/ "./src/main/webapp/app/modules/administration/health/health-modal.tsx":
/*!****************************************************************************!*\
  !*** ./src/main/webapp/app/modules/administration/health/health-modal.tsx ***!
  \****************************************************************************/
/*! exports provided: default */
/***/ (function(module, __webpack_exports__, __webpack_require__) {

"use strict";
__webpack_require__.r(__webpack_exports__);
/* harmony import */ var react__WEBPACK_IMPORTED_MODULE_0__ = __webpack_require__(/*! react */ "./node_modules/react/index.js");
/* harmony import */ var react__WEBPACK_IMPORTED_MODULE_0___default = /*#__PURE__*/__webpack_require__.n(react__WEBPACK_IMPORTED_MODULE_0__);
/* harmony import */ var reactstrap__WEBPACK_IMPORTED_MODULE_1__ = __webpack_require__(/*! reactstrap */ "./node_modules/reactstrap/es/index.js");


const formatDiskSpaceOutput = rawValue => {
    // Should display storage space in an human readable unit
    const val = rawValue / 1073741824;
    if (val > 1) {
        // Value
        return val.toFixed(2) + ' GB';
    }
    else {
        return (rawValue / 1048576).toFixed(2) + ' MB';
    }
};
const HealthModal = ({ handleClose, healthObject, showModal }) => {
    const data = healthObject.details || {};
    return (react__WEBPACK_IMPORTED_MODULE_0___default.a.createElement(reactstrap__WEBPACK_IMPORTED_MODULE_1__["Modal"], { isOpen: showModal, modalTransition: { timeout: 20 }, backdropTransition: { timeout: 10 }, toggle: handleClose },
        react__WEBPACK_IMPORTED_MODULE_0___default.a.createElement(reactstrap__WEBPACK_IMPORTED_MODULE_1__["ModalHeader"], { toggle: handleClose }, healthObject.name),
        react__WEBPACK_IMPORTED_MODULE_0___default.a.createElement(reactstrap__WEBPACK_IMPORTED_MODULE_1__["ModalBody"], null,
            react__WEBPACK_IMPORTED_MODULE_0___default.a.createElement(reactstrap__WEBPACK_IMPORTED_MODULE_1__["Table"], { bordered: true },
                react__WEBPACK_IMPORTED_MODULE_0___default.a.createElement("thead", null,
                    react__WEBPACK_IMPORTED_MODULE_0___default.a.createElement("tr", null,
                        react__WEBPACK_IMPORTED_MODULE_0___default.a.createElement("th", null, "Name"),
                        react__WEBPACK_IMPORTED_MODULE_0___default.a.createElement("th", null, "Value"))),
                react__WEBPACK_IMPORTED_MODULE_0___default.a.createElement("tbody", null, Object.keys(data).map((key, index) => (react__WEBPACK_IMPORTED_MODULE_0___default.a.createElement("tr", { key: index },
                    react__WEBPACK_IMPORTED_MODULE_0___default.a.createElement("td", null, key),
                    react__WEBPACK_IMPORTED_MODULE_0___default.a.createElement("td", null, healthObject.name === 'diskSpace' ? formatDiskSpaceOutput(data[key]) : JSON.stringify(data[key])))))))),
        react__WEBPACK_IMPORTED_MODULE_0___default.a.createElement(reactstrap__WEBPACK_IMPORTED_MODULE_1__["ModalFooter"], null,
            react__WEBPACK_IMPORTED_MODULE_0___default.a.createElement(reactstrap__WEBPACK_IMPORTED_MODULE_1__["Button"], { color: "primary", onClick: handleClose }, "Close"))));
};
/* harmony default export */ __webpack_exports__["default"] = (HealthModal);

 void function register() { /* react-hot-loader/webpack */ var reactHotLoader = typeof reactHotLoaderGlobal !== 'undefined' ? reactHotLoaderGlobal.default : undefined; if (!reactHotLoader) { return; } /* eslint-disable camelcase, no-undef */ var webpackExports = typeof __webpack_exports__ !== 'undefined' ? __webpack_exports__ : exports; /* eslint-enable camelcase, no-undef */ if (!webpackExports) { return; } if (typeof webpackExports === 'function') { reactHotLoader.register(webpackExports, 'module.exports', "C:\\gateway\\src\\main\\webapp\\app\\modules\\administration\\health\\health-modal.tsx"); return; } /* eslint-disable no-restricted-syntax */ for (var key in webpackExports) { /* eslint-enable no-restricted-syntax */ if (!Object.prototype.hasOwnProperty.call(webpackExports, key)) { continue; } var namedExport = void 0; try { namedExport = webpackExports[key]; } catch (err) { continue; } reactHotLoader.register(namedExport, key, "C:\\gateway\\src\\main\\webapp\\app\\modules\\administration\\health\\health-modal.tsx"); } }(); 

/***/ }),

/***/ "./src/main/webapp/app/modules/administration/health/health.tsx":
/*!**********************************************************************!*\
  !*** ./src/main/webapp/app/modules/administration/health/health.tsx ***!
  \**********************************************************************/
/*! exports provided: HealthPage, default */
/***/ (function(module, __webpack_exports__, __webpack_require__) {

"use strict";
__webpack_require__.r(__webpack_exports__);
/* harmony export (binding) */ __webpack_require__.d(__webpack_exports__, "HealthPage", function() { return HealthPage; });
/* harmony import */ var react__WEBPACK_IMPORTED_MODULE_0__ = __webpack_require__(/*! react */ "./node_modules/react/index.js");
/* harmony import */ var react__WEBPACK_IMPORTED_MODULE_0___default = /*#__PURE__*/__webpack_require__.n(react__WEBPACK_IMPORTED_MODULE_0__);
/* harmony import */ var react_redux__WEBPACK_IMPORTED_MODULE_1__ = __webpack_require__(/*! react-redux */ "./node_modules/react-redux/es/index.js");
/* harmony import */ var react_jhipster__WEBPACK_IMPORTED_MODULE_2__ = __webpack_require__(/*! react-jhipster */ "./node_modules/react-jhipster/lib/index.js");
/* harmony import */ var react_jhipster__WEBPACK_IMPORTED_MODULE_2___default = /*#__PURE__*/__webpack_require__.n(react_jhipster__WEBPACK_IMPORTED_MODULE_2__);
/* harmony import */ var reactstrap__WEBPACK_IMPORTED_MODULE_3__ = __webpack_require__(/*! reactstrap */ "./node_modules/reactstrap/es/index.js");
/* harmony import */ var _fortawesome_react_fontawesome__WEBPACK_IMPORTED_MODULE_4__ = __webpack_require__(/*! @fortawesome/react-fontawesome */ "./node_modules/@fortawesome/react-fontawesome/index.es.js");
/* harmony import */ var _administration_reducer__WEBPACK_IMPORTED_MODULE_5__ = __webpack_require__(/*! ../administration.reducer */ "./src/main/webapp/app/modules/administration/administration.reducer.ts");
/* harmony import */ var _health_modal__WEBPACK_IMPORTED_MODULE_6__ = __webpack_require__(/*! ./health-modal */ "./src/main/webapp/app/modules/administration/health/health-modal.tsx");







const HealthPage = (props) => {
    const [healthObject, setHealthObject] = Object(react__WEBPACK_IMPORTED_MODULE_0__["useState"])({});
    const [showModal, setShowModal] = Object(react__WEBPACK_IMPORTED_MODULE_0__["useState"])(false);
    Object(react__WEBPACK_IMPORTED_MODULE_0__["useEffect"])(() => {
        props.systemHealth();
    }, []);
    const getSystemHealth = () => {
        if (!props.isFetching) {
            props.systemHealth();
        }
    };
    const getSystemHealthInfo = (name, healthObj) => () => {
        setShowModal(true);
        setHealthObject(Object.assign(Object.assign({}, healthObj), { name }));
    };
    const handleClose = () => setShowModal(false);
    const renderModal = () => react__WEBPACK_IMPORTED_MODULE_0___default.a.createElement(_health_modal__WEBPACK_IMPORTED_MODULE_6__["default"], { healthObject: healthObject, handleClose: handleClose, showModal: showModal });
    const { health, isFetching } = props;
    const data = (health || {}).components || {};
    return (react__WEBPACK_IMPORTED_MODULE_0___default.a.createElement("div", null,
        react__WEBPACK_IMPORTED_MODULE_0___default.a.createElement("h2", { id: "health-page-heading" }, "Health Checks"),
        react__WEBPACK_IMPORTED_MODULE_0___default.a.createElement("p", null,
            react__WEBPACK_IMPORTED_MODULE_0___default.a.createElement(reactstrap__WEBPACK_IMPORTED_MODULE_3__["Button"], { onClick: getSystemHealth, color: isFetching ? 'btn btn-danger' : 'btn btn-primary', disabled: isFetching },
                react__WEBPACK_IMPORTED_MODULE_0___default.a.createElement(_fortawesome_react_fontawesome__WEBPACK_IMPORTED_MODULE_4__["FontAwesomeIcon"], { icon: "sync" }),
                "\u00A0",
                react__WEBPACK_IMPORTED_MODULE_0___default.a.createElement(react_jhipster__WEBPACK_IMPORTED_MODULE_2__["Translate"], { component: "span", contentKey: "health.refresh.button" }, "Refresh"))),
        react__WEBPACK_IMPORTED_MODULE_0___default.a.createElement(reactstrap__WEBPACK_IMPORTED_MODULE_3__["Row"], null,
            react__WEBPACK_IMPORTED_MODULE_0___default.a.createElement(reactstrap__WEBPACK_IMPORTED_MODULE_3__["Col"], { md: "12" },
                react__WEBPACK_IMPORTED_MODULE_0___default.a.createElement(reactstrap__WEBPACK_IMPORTED_MODULE_3__["Table"], { bordered: true, "aria-describedby": "health-page-heading" },
                    react__WEBPACK_IMPORTED_MODULE_0___default.a.createElement("thead", null,
                        react__WEBPACK_IMPORTED_MODULE_0___default.a.createElement("tr", null,
                            react__WEBPACK_IMPORTED_MODULE_0___default.a.createElement("th", null, "Service Name"),
                            react__WEBPACK_IMPORTED_MODULE_0___default.a.createElement("th", null, "Status"),
                            react__WEBPACK_IMPORTED_MODULE_0___default.a.createElement("th", null, "Details"))),
                    react__WEBPACK_IMPORTED_MODULE_0___default.a.createElement("tbody", null, Object.keys(data).map((configPropKey, configPropIndex) => configPropKey !== 'status' ? (react__WEBPACK_IMPORTED_MODULE_0___default.a.createElement("tr", { key: configPropIndex },
                        react__WEBPACK_IMPORTED_MODULE_0___default.a.createElement("td", null, configPropKey),
                        react__WEBPACK_IMPORTED_MODULE_0___default.a.createElement("td", null,
                            react__WEBPACK_IMPORTED_MODULE_0___default.a.createElement(reactstrap__WEBPACK_IMPORTED_MODULE_3__["Badge"], { color: data[configPropKey].status !== 'UP' ? 'danger' : 'success' }, data[configPropKey].status)),
                        react__WEBPACK_IMPORTED_MODULE_0___default.a.createElement("td", null, data[configPropKey].details ? (react__WEBPACK_IMPORTED_MODULE_0___default.a.createElement("a", { onClick: getSystemHealthInfo(configPropKey, data[configPropKey]) },
                            react__WEBPACK_IMPORTED_MODULE_0___default.a.createElement(_fortawesome_react_fontawesome__WEBPACK_IMPORTED_MODULE_4__["FontAwesomeIcon"], { icon: "eye" }))) : null))) : null))))),
        renderModal()));
};
const mapStateToProps = (storeState) => ({
    health: storeState.administration.health,
    isFetching: storeState.administration.loading,
});
const mapDispatchToProps = { systemHealth: _administration_reducer__WEBPACK_IMPORTED_MODULE_5__["systemHealth"] };
/* harmony default export */ __webpack_exports__["default"] = (Object(react_redux__WEBPACK_IMPORTED_MODULE_1__["connect"])(mapStateToProps, mapDispatchToProps)(HealthPage));

 void function register() { /* react-hot-loader/webpack */ var reactHotLoader = typeof reactHotLoaderGlobal !== 'undefined' ? reactHotLoaderGlobal.default : undefined; if (!reactHotLoader) { return; } /* eslint-disable camelcase, no-undef */ var webpackExports = typeof __webpack_exports__ !== 'undefined' ? __webpack_exports__ : exports; /* eslint-enable camelcase, no-undef */ if (!webpackExports) { return; } if (typeof webpackExports === 'function') { reactHotLoader.register(webpackExports, 'module.exports', "C:\\gateway\\src\\main\\webapp\\app\\modules\\administration\\health\\health.tsx"); return; } /* eslint-disable no-restricted-syntax */ for (var key in webpackExports) { /* eslint-enable no-restricted-syntax */ if (!Object.prototype.hasOwnProperty.call(webpackExports, key)) { continue; } var namedExport = void 0; try { namedExport = webpackExports[key]; } catch (err) { continue; } reactHotLoader.register(namedExport, key, "C:\\gateway\\src\\main\\webapp\\app\\modules\\administration\\health\\health.tsx"); } }(); 

/***/ }),

/***/ "./src/main/webapp/app/modules/administration/index.tsx":
/*!**************************************************************!*\
  !*** ./src/main/webapp/app/modules/administration/index.tsx ***!
  \**************************************************************/
/*! exports provided: default */
/***/ (function(module, __webpack_exports__, __webpack_require__) {

"use strict";
__webpack_require__.r(__webpack_exports__);
/* harmony import */ var react__WEBPACK_IMPORTED_MODULE_0__ = __webpack_require__(/*! react */ "./node_modules/react/index.js");
/* harmony import */ var react__WEBPACK_IMPORTED_MODULE_0___default = /*#__PURE__*/__webpack_require__.n(react__WEBPACK_IMPORTED_MODULE_0__);
/* harmony import */ var app_shared_error_error_boundary_route__WEBPACK_IMPORTED_MODULE_1__ = __webpack_require__(/*! app/shared/error/error-boundary-route */ "./src/main/webapp/app/shared/error/error-boundary-route.tsx");
/* harmony import */ var _user_management__WEBPACK_IMPORTED_MODULE_2__ = __webpack_require__(/*! ./user-management */ "./src/main/webapp/app/modules/administration/user-management/index.tsx");
/* harmony import */ var _logs_logs__WEBPACK_IMPORTED_MODULE_3__ = __webpack_require__(/*! ./logs/logs */ "./src/main/webapp/app/modules/administration/logs/logs.tsx");
/* harmony import */ var _health_health__WEBPACK_IMPORTED_MODULE_4__ = __webpack_require__(/*! ./health/health */ "./src/main/webapp/app/modules/administration/health/health.tsx");
/* harmony import */ var _metrics_metrics__WEBPACK_IMPORTED_MODULE_5__ = __webpack_require__(/*! ./metrics/metrics */ "./src/main/webapp/app/modules/administration/metrics/metrics.tsx");
/* harmony import */ var _configuration_configuration__WEBPACK_IMPORTED_MODULE_6__ = __webpack_require__(/*! ./configuration/configuration */ "./src/main/webapp/app/modules/administration/configuration/configuration.tsx");
/* harmony import */ var _audits_audits__WEBPACK_IMPORTED_MODULE_7__ = __webpack_require__(/*! ./audits/audits */ "./src/main/webapp/app/modules/administration/audits/audits.tsx");
/* harmony import */ var _docs_docs__WEBPACK_IMPORTED_MODULE_8__ = __webpack_require__(/*! ./docs/docs */ "./src/main/webapp/app/modules/administration/docs/docs.tsx");
/* harmony import */ var _gateway_gateway__WEBPACK_IMPORTED_MODULE_9__ = __webpack_require__(/*! ./gateway/gateway */ "./src/main/webapp/app/modules/administration/gateway/gateway.tsx");










const Routes = ({ match }) => (react__WEBPACK_IMPORTED_MODULE_0___default.a.createElement("div", null,
    react__WEBPACK_IMPORTED_MODULE_0___default.a.createElement(app_shared_error_error_boundary_route__WEBPACK_IMPORTED_MODULE_1__["default"], { path: `${match.url}/user-management`, component: _user_management__WEBPACK_IMPORTED_MODULE_2__["default"] }),
    react__WEBPACK_IMPORTED_MODULE_0___default.a.createElement(app_shared_error_error_boundary_route__WEBPACK_IMPORTED_MODULE_1__["default"], { exact: true, path: `${match.url}/health`, component: _health_health__WEBPACK_IMPORTED_MODULE_4__["default"] }),
    react__WEBPACK_IMPORTED_MODULE_0___default.a.createElement(app_shared_error_error_boundary_route__WEBPACK_IMPORTED_MODULE_1__["default"], { exact: true, path: `${match.url}/gateway`, component: _gateway_gateway__WEBPACK_IMPORTED_MODULE_9__["default"] }),
    react__WEBPACK_IMPORTED_MODULE_0___default.a.createElement(app_shared_error_error_boundary_route__WEBPACK_IMPORTED_MODULE_1__["default"], { exact: true, path: `${match.url}/metrics`, component: _metrics_metrics__WEBPACK_IMPORTED_MODULE_5__["default"] }),
    react__WEBPACK_IMPORTED_MODULE_0___default.a.createElement(app_shared_error_error_boundary_route__WEBPACK_IMPORTED_MODULE_1__["default"], { exact: true, path: `${match.url}/docs`, component: _docs_docs__WEBPACK_IMPORTED_MODULE_8__["default"] }),
    react__WEBPACK_IMPORTED_MODULE_0___default.a.createElement(app_shared_error_error_boundary_route__WEBPACK_IMPORTED_MODULE_1__["default"], { exact: true, path: `${match.url}/configuration`, component: _configuration_configuration__WEBPACK_IMPORTED_MODULE_6__["default"] }),
    react__WEBPACK_IMPORTED_MODULE_0___default.a.createElement(app_shared_error_error_boundary_route__WEBPACK_IMPORTED_MODULE_1__["default"], { exact: true, path: `${match.url}/audits`, component: _audits_audits__WEBPACK_IMPORTED_MODULE_7__["default"] }),
    react__WEBPACK_IMPORTED_MODULE_0___default.a.createElement(app_shared_error_error_boundary_route__WEBPACK_IMPORTED_MODULE_1__["default"], { exact: true, path: `${match.url}/logs`, component: _logs_logs__WEBPACK_IMPORTED_MODULE_3__["default"] })));
/* harmony default export */ __webpack_exports__["default"] = (Routes);

 void function register() { /* react-hot-loader/webpack */ var reactHotLoader = typeof reactHotLoaderGlobal !== 'undefined' ? reactHotLoaderGlobal.default : undefined; if (!reactHotLoader) { return; } /* eslint-disable camelcase, no-undef */ var webpackExports = typeof __webpack_exports__ !== 'undefined' ? __webpack_exports__ : exports; /* eslint-enable camelcase, no-undef */ if (!webpackExports) { return; } if (typeof webpackExports === 'function') { reactHotLoader.register(webpackExports, 'module.exports', "C:\\gateway\\src\\main\\webapp\\app\\modules\\administration\\index.tsx"); return; } /* eslint-disable no-restricted-syntax */ for (var key in webpackExports) { /* eslint-enable no-restricted-syntax */ if (!Object.prototype.hasOwnProperty.call(webpackExports, key)) { continue; } var namedExport = void 0; try { namedExport = webpackExports[key]; } catch (err) { continue; } reactHotLoader.register(namedExport, key, "C:\\gateway\\src\\main\\webapp\\app\\modules\\administration\\index.tsx"); } }(); 

/***/ }),

/***/ "./src/main/webapp/app/modules/administration/logs/logs.tsx":
/*!******************************************************************!*\
  !*** ./src/main/webapp/app/modules/administration/logs/logs.tsx ***!
  \******************************************************************/
/*! exports provided: LogsPage, default */
/***/ (function(module, __webpack_exports__, __webpack_require__) {

"use strict";
__webpack_require__.r(__webpack_exports__);
/* harmony export (binding) */ __webpack_require__.d(__webpack_exports__, "LogsPage", function() { return LogsPage; });
/* harmony import */ var react__WEBPACK_IMPORTED_MODULE_0__ = __webpack_require__(/*! react */ "./node_modules/react/index.js");
/* harmony import */ var react__WEBPACK_IMPORTED_MODULE_0___default = /*#__PURE__*/__webpack_require__.n(react__WEBPACK_IMPORTED_MODULE_0__);
/* harmony import */ var react_redux__WEBPACK_IMPORTED_MODULE_1__ = __webpack_require__(/*! react-redux */ "./node_modules/react-redux/es/index.js");
/* harmony import */ var react_jhipster__WEBPACK_IMPORTED_MODULE_2__ = __webpack_require__(/*! react-jhipster */ "./node_modules/react-jhipster/lib/index.js");
/* harmony import */ var react_jhipster__WEBPACK_IMPORTED_MODULE_2___default = /*#__PURE__*/__webpack_require__.n(react_jhipster__WEBPACK_IMPORTED_MODULE_2__);
/* harmony import */ var _administration_reducer__WEBPACK_IMPORTED_MODULE_3__ = __webpack_require__(/*! ../administration.reducer */ "./src/main/webapp/app/modules/administration/administration.reducer.ts");




const LogsPage = (props) => {
    const [filter, setFilter] = Object(react__WEBPACK_IMPORTED_MODULE_0__["useState"])('');
    Object(react__WEBPACK_IMPORTED_MODULE_0__["useEffect"])(() => {
        props.getLoggers();
    }, []);
    const getLogs = () => {
        if (!props.isFetching) {
            props.getLoggers();
        }
    };
    const changeLevel = (loggerName, level) => () => props.changeLogLevel(loggerName, level);
    const changeFilter = evt => setFilter(evt.target.value);
    const getClassName = (level, check, className) => (level === check ? `btn btn-sm btn-${className}` : 'btn btn-sm btn-light');
    const filterFn = l => l.name.toUpperCase().includes(filter.toUpperCase());
    const { logs, isFetching } = props;
    const loggers = logs ? Object.entries(logs.loggers).map(e => ({ name: e[0], level: e[1].effectiveLevel })) : [];
    return (react__WEBPACK_IMPORTED_MODULE_0___default.a.createElement("div", null,
        react__WEBPACK_IMPORTED_MODULE_0___default.a.createElement("h2", { id: "logs-page-heading" },
            react__WEBPACK_IMPORTED_MODULE_0___default.a.createElement(react_jhipster__WEBPACK_IMPORTED_MODULE_2__["Translate"], { contentKey: "logs.title" }, "Logs")),
        react__WEBPACK_IMPORTED_MODULE_0___default.a.createElement("p", null,
            react__WEBPACK_IMPORTED_MODULE_0___default.a.createElement(react_jhipster__WEBPACK_IMPORTED_MODULE_2__["Translate"], { contentKey: "logs.nbloggers", interpolate: { total: loggers.length } },
                "There are ",
                loggers.length.toString(),
                " loggers.")),
        react__WEBPACK_IMPORTED_MODULE_0___default.a.createElement("span", null,
            react__WEBPACK_IMPORTED_MODULE_0___default.a.createElement(react_jhipster__WEBPACK_IMPORTED_MODULE_2__["Translate"], { contentKey: "logs.filter" }, "Filter")),
        react__WEBPACK_IMPORTED_MODULE_0___default.a.createElement("input", { type: "text", value: filter, onChange: changeFilter, className: "form-control", disabled: isFetching }),
        react__WEBPACK_IMPORTED_MODULE_0___default.a.createElement("table", { className: "table table-sm table-striped table-bordered", "aria-describedby": "logs-page-heading" },
            react__WEBPACK_IMPORTED_MODULE_0___default.a.createElement("thead", null,
                react__WEBPACK_IMPORTED_MODULE_0___default.a.createElement("tr", { title: "click to order" },
                    react__WEBPACK_IMPORTED_MODULE_0___default.a.createElement("th", null,
                        react__WEBPACK_IMPORTED_MODULE_0___default.a.createElement("span", null,
                            react__WEBPACK_IMPORTED_MODULE_0___default.a.createElement(react_jhipster__WEBPACK_IMPORTED_MODULE_2__["Translate"], { contentKey: "logs.table.name" }, "Name"))),
                    react__WEBPACK_IMPORTED_MODULE_0___default.a.createElement("th", null,
                        react__WEBPACK_IMPORTED_MODULE_0___default.a.createElement("span", null,
                            react__WEBPACK_IMPORTED_MODULE_0___default.a.createElement(react_jhipster__WEBPACK_IMPORTED_MODULE_2__["Translate"], { contentKey: "logs.table.level" }, "Level"))))),
            react__WEBPACK_IMPORTED_MODULE_0___default.a.createElement("tbody", null, loggers.filter(filterFn).map((logger, i) => (react__WEBPACK_IMPORTED_MODULE_0___default.a.createElement("tr", { key: `log-row-${i}` },
                react__WEBPACK_IMPORTED_MODULE_0___default.a.createElement("td", null,
                    react__WEBPACK_IMPORTED_MODULE_0___default.a.createElement("small", null, logger.name)),
                react__WEBPACK_IMPORTED_MODULE_0___default.a.createElement("td", null,
                    react__WEBPACK_IMPORTED_MODULE_0___default.a.createElement("button", { disabled: isFetching, onClick: changeLevel(logger.name, 'TRACE'), className: getClassName(logger.level, 'TRACE', 'primary') }, "TRACE"),
                    react__WEBPACK_IMPORTED_MODULE_0___default.a.createElement("button", { disabled: isFetching, onClick: changeLevel(logger.name, 'DEBUG'), className: getClassName(logger.level, 'DEBUG', 'success') }, "DEBUG"),
                    react__WEBPACK_IMPORTED_MODULE_0___default.a.createElement("button", { disabled: isFetching, onClick: changeLevel(logger.name, 'INFO'), className: getClassName(logger.level, 'INFO', 'info') }, "INFO"),
                    react__WEBPACK_IMPORTED_MODULE_0___default.a.createElement("button", { disabled: isFetching, onClick: changeLevel(logger.name, 'WARN'), className: getClassName(logger.level, 'WARN', 'warning') }, "WARN"),
                    react__WEBPACK_IMPORTED_MODULE_0___default.a.createElement("button", { disabled: isFetching, onClick: changeLevel(logger.name, 'ERROR'), className: getClassName(logger.level, 'ERROR', 'danger') }, "ERROR"),
                    react__WEBPACK_IMPORTED_MODULE_0___default.a.createElement("button", { disabled: isFetching, onClick: changeLevel(logger.name, 'OFF'), className: getClassName(logger.level, 'OFF', 'secondary') }, "OFF")))))))));
};
const mapStateToProps = ({ administration }) => ({
    logs: administration.logs,
    isFetching: administration.loading,
});
const mapDispatchToProps = { getLoggers: _administration_reducer__WEBPACK_IMPORTED_MODULE_3__["getLoggers"], changeLogLevel: _administration_reducer__WEBPACK_IMPORTED_MODULE_3__["changeLogLevel"] };
/* harmony default export */ __webpack_exports__["default"] = (Object(react_redux__WEBPACK_IMPORTED_MODULE_1__["connect"])(mapStateToProps, mapDispatchToProps)(LogsPage));

 void function register() { /* react-hot-loader/webpack */ var reactHotLoader = typeof reactHotLoaderGlobal !== 'undefined' ? reactHotLoaderGlobal.default : undefined; if (!reactHotLoader) { return; } /* eslint-disable camelcase, no-undef */ var webpackExports = typeof __webpack_exports__ !== 'undefined' ? __webpack_exports__ : exports; /* eslint-enable camelcase, no-undef */ if (!webpackExports) { return; } if (typeof webpackExports === 'function') { reactHotLoader.register(webpackExports, 'module.exports', "C:\\gateway\\src\\main\\webapp\\app\\modules\\administration\\logs\\logs.tsx"); return; } /* eslint-disable no-restricted-syntax */ for (var key in webpackExports) { /* eslint-enable no-restricted-syntax */ if (!Object.prototype.hasOwnProperty.call(webpackExports, key)) { continue; } var namedExport = void 0; try { namedExport = webpackExports[key]; } catch (err) { continue; } reactHotLoader.register(namedExport, key, "C:\\gateway\\src\\main\\webapp\\app\\modules\\administration\\logs\\logs.tsx"); } }(); 

/***/ }),

/***/ "./src/main/webapp/app/modules/administration/metrics/metrics.tsx":
/*!************************************************************************!*\
  !*** ./src/main/webapp/app/modules/administration/metrics/metrics.tsx ***!
  \************************************************************************/
/*! exports provided: MetricsPage, default */
/***/ (function(module, __webpack_exports__, __webpack_require__) {

"use strict";
__webpack_require__.r(__webpack_exports__);
/* harmony export (binding) */ __webpack_require__.d(__webpack_exports__, "MetricsPage", function() { return MetricsPage; });
/* harmony import */ var react__WEBPACK_IMPORTED_MODULE_0__ = __webpack_require__(/*! react */ "./node_modules/react/index.js");
/* harmony import */ var react__WEBPACK_IMPORTED_MODULE_0___default = /*#__PURE__*/__webpack_require__.n(react__WEBPACK_IMPORTED_MODULE_0__);
/* harmony import */ var react_redux__WEBPACK_IMPORTED_MODULE_1__ = __webpack_require__(/*! react-redux */ "./node_modules/react-redux/es/index.js");
/* harmony import */ var reactstrap__WEBPACK_IMPORTED_MODULE_2__ = __webpack_require__(/*! reactstrap */ "./node_modules/reactstrap/es/index.js");
/* harmony import */ var react_jhipster__WEBPACK_IMPORTED_MODULE_3__ = __webpack_require__(/*! react-jhipster */ "./node_modules/react-jhipster/lib/index.js");
/* harmony import */ var react_jhipster__WEBPACK_IMPORTED_MODULE_3___default = /*#__PURE__*/__webpack_require__.n(react_jhipster__WEBPACK_IMPORTED_MODULE_3__);
/* harmony import */ var _fortawesome_react_fontawesome__WEBPACK_IMPORTED_MODULE_4__ = __webpack_require__(/*! @fortawesome/react-fontawesome */ "./node_modules/@fortawesome/react-fontawesome/index.es.js");
/* harmony import */ var app_config_constants__WEBPACK_IMPORTED_MODULE_5__ = __webpack_require__(/*! app/config/constants */ "./src/main/webapp/app/config/constants.ts");
/* harmony import */ var _administration_reducer__WEBPACK_IMPORTED_MODULE_6__ = __webpack_require__(/*! ../administration.reducer */ "./src/main/webapp/app/modules/administration/administration.reducer.ts");







const MetricsPage = (props) => {
    const [showModal, setShowModal] = Object(react__WEBPACK_IMPORTED_MODULE_0__["useState"])(false);
    Object(react__WEBPACK_IMPORTED_MODULE_0__["useEffect"])(() => {
        props.systemMetrics();
        props.systemThreadDump();
    }, []);
    const getMetrics = () => {
        if (!props.isFetching) {
            props.systemMetrics();
            props.systemThreadDump();
        }
    };
    const { metrics, threadDump, isFetching } = props;
    return (react__WEBPACK_IMPORTED_MODULE_0___default.a.createElement("div", null,
        react__WEBPACK_IMPORTED_MODULE_0___default.a.createElement("h2", { id: "metrics-page-heading" }, "Application Metrics"),
        react__WEBPACK_IMPORTED_MODULE_0___default.a.createElement("p", null,
            react__WEBPACK_IMPORTED_MODULE_0___default.a.createElement(reactstrap__WEBPACK_IMPORTED_MODULE_2__["Button"], { onClick: getMetrics, color: isFetching ? 'btn btn-danger' : 'btn btn-primary', disabled: isFetching },
                react__WEBPACK_IMPORTED_MODULE_0___default.a.createElement(_fortawesome_react_fontawesome__WEBPACK_IMPORTED_MODULE_4__["FontAwesomeIcon"], { icon: "sync" }),
                "\u00A0",
                react__WEBPACK_IMPORTED_MODULE_0___default.a.createElement(react_jhipster__WEBPACK_IMPORTED_MODULE_3__["Translate"], { component: "span", contentKey: "health.refresh.button" }, "Refresh"))),
        react__WEBPACK_IMPORTED_MODULE_0___default.a.createElement("hr", null),
        react__WEBPACK_IMPORTED_MODULE_0___default.a.createElement(reactstrap__WEBPACK_IMPORTED_MODULE_2__["Row"], null,
            react__WEBPACK_IMPORTED_MODULE_0___default.a.createElement(reactstrap__WEBPACK_IMPORTED_MODULE_2__["Col"], { sm: "12" },
                react__WEBPACK_IMPORTED_MODULE_0___default.a.createElement("h3", null, "JVM Metrics"),
                react__WEBPACK_IMPORTED_MODULE_0___default.a.createElement(reactstrap__WEBPACK_IMPORTED_MODULE_2__["Row"], null,
                    react__WEBPACK_IMPORTED_MODULE_0___default.a.createElement(reactstrap__WEBPACK_IMPORTED_MODULE_2__["Col"], { md: "4" }, metrics && metrics.jvm ? react__WEBPACK_IMPORTED_MODULE_0___default.a.createElement(react_jhipster__WEBPACK_IMPORTED_MODULE_3__["JvmMemory"], { jvmMetrics: metrics.jvm, wholeNumberFormat: app_config_constants__WEBPACK_IMPORTED_MODULE_5__["APP_WHOLE_NUMBER_FORMAT"] }) : ''),
                    react__WEBPACK_IMPORTED_MODULE_0___default.a.createElement(reactstrap__WEBPACK_IMPORTED_MODULE_2__["Col"], { md: "4" }, threadDump ? react__WEBPACK_IMPORTED_MODULE_0___default.a.createElement(react_jhipster__WEBPACK_IMPORTED_MODULE_3__["JvmThreads"], { jvmThreads: threadDump, wholeNumberFormat: app_config_constants__WEBPACK_IMPORTED_MODULE_5__["APP_WHOLE_NUMBER_FORMAT"] }) : ''),
                    react__WEBPACK_IMPORTED_MODULE_0___default.a.createElement(reactstrap__WEBPACK_IMPORTED_MODULE_2__["Col"], { md: "4" }, metrics && metrics.processMetrics ? (react__WEBPACK_IMPORTED_MODULE_0___default.a.createElement(react_jhipster__WEBPACK_IMPORTED_MODULE_3__["SystemMetrics"], { systemMetrics: metrics.processMetrics, wholeNumberFormat: app_config_constants__WEBPACK_IMPORTED_MODULE_5__["APP_WHOLE_NUMBER_FORMAT"], timestampFormat: app_config_constants__WEBPACK_IMPORTED_MODULE_5__["APP_TIMESTAMP_FORMAT"] })) : (''))))),
        metrics && metrics.garbageCollector ? (react__WEBPACK_IMPORTED_MODULE_0___default.a.createElement(react_jhipster__WEBPACK_IMPORTED_MODULE_3__["GarbageCollectorMetrics"], { garbageCollectorMetrics: metrics.garbageCollector, wholeNumberFormat: app_config_constants__WEBPACK_IMPORTED_MODULE_5__["APP_WHOLE_NUMBER_FORMAT"] })) : (''),
        metrics && metrics['http.server.requests'] ? (react__WEBPACK_IMPORTED_MODULE_0___default.a.createElement(react_jhipster__WEBPACK_IMPORTED_MODULE_3__["HttpRequestMetrics"], { requestMetrics: metrics['http.server.requests'], twoDigitAfterPointFormat: app_config_constants__WEBPACK_IMPORTED_MODULE_5__["APP_TWO_DIGITS_AFTER_POINT_NUMBER_FORMAT"], wholeNumberFormat: app_config_constants__WEBPACK_IMPORTED_MODULE_5__["APP_WHOLE_NUMBER_FORMAT"] })) : (''),
        metrics && metrics.services ? (react__WEBPACK_IMPORTED_MODULE_0___default.a.createElement(react_jhipster__WEBPACK_IMPORTED_MODULE_3__["EndpointsRequestsMetrics"], { endpointsRequestsMetrics: metrics.services, wholeNumberFormat: app_config_constants__WEBPACK_IMPORTED_MODULE_5__["APP_WHOLE_NUMBER_FORMAT"] })) : (''),
        metrics.cache ? (react__WEBPACK_IMPORTED_MODULE_0___default.a.createElement(reactstrap__WEBPACK_IMPORTED_MODULE_2__["Row"], null,
            react__WEBPACK_IMPORTED_MODULE_0___default.a.createElement(reactstrap__WEBPACK_IMPORTED_MODULE_2__["Col"], { sm: "12" },
                react__WEBPACK_IMPORTED_MODULE_0___default.a.createElement(react_jhipster__WEBPACK_IMPORTED_MODULE_3__["CacheMetrics"], { cacheMetrics: metrics.cache, twoDigitAfterPointFormat: app_config_constants__WEBPACK_IMPORTED_MODULE_5__["APP_TWO_DIGITS_AFTER_POINT_NUMBER_FORMAT"] })))) : (''),
        metrics.databases && JSON.stringify(metrics.databases) !== '{}' ? (react__WEBPACK_IMPORTED_MODULE_0___default.a.createElement(reactstrap__WEBPACK_IMPORTED_MODULE_2__["Row"], null,
            react__WEBPACK_IMPORTED_MODULE_0___default.a.createElement(reactstrap__WEBPACK_IMPORTED_MODULE_2__["Col"], { sm: "12" },
                react__WEBPACK_IMPORTED_MODULE_0___default.a.createElement(react_jhipster__WEBPACK_IMPORTED_MODULE_3__["DatasourceMetrics"], { datasourceMetrics: metrics.databases, twoDigitAfterPointFormat: app_config_constants__WEBPACK_IMPORTED_MODULE_5__["APP_TWO_DIGITS_AFTER_POINT_NUMBER_FORMAT"] })))) : ('')));
};
const mapStateToProps = (storeState) => ({
    metrics: storeState.administration.metrics,
    isFetching: storeState.administration.loading,
    threadDump: storeState.administration.threadDump,
});
const mapDispatchToProps = { systemMetrics: _administration_reducer__WEBPACK_IMPORTED_MODULE_6__["systemMetrics"], systemThreadDump: _administration_reducer__WEBPACK_IMPORTED_MODULE_6__["systemThreadDump"] };
/* harmony default export */ __webpack_exports__["default"] = (Object(react_redux__WEBPACK_IMPORTED_MODULE_1__["connect"])(mapStateToProps, mapDispatchToProps)(MetricsPage));

 void function register() { /* react-hot-loader/webpack */ var reactHotLoader = typeof reactHotLoaderGlobal !== 'undefined' ? reactHotLoaderGlobal.default : undefined; if (!reactHotLoader) { return; } /* eslint-disable camelcase, no-undef */ var webpackExports = typeof __webpack_exports__ !== 'undefined' ? __webpack_exports__ : exports; /* eslint-enable camelcase, no-undef */ if (!webpackExports) { return; } if (typeof webpackExports === 'function') { reactHotLoader.register(webpackExports, 'module.exports', "C:\\gateway\\src\\main\\webapp\\app\\modules\\administration\\metrics\\metrics.tsx"); return; } /* eslint-disable no-restricted-syntax */ for (var key in webpackExports) { /* eslint-enable no-restricted-syntax */ if (!Object.prototype.hasOwnProperty.call(webpackExports, key)) { continue; } var namedExport = void 0; try { namedExport = webpackExports[key]; } catch (err) { continue; } reactHotLoader.register(namedExport, key, "C:\\gateway\\src\\main\\webapp\\app\\modules\\administration\\metrics\\metrics.tsx"); } }(); 

/***/ }),

/***/ "./src/main/webapp/app/modules/administration/user-management/index.tsx":
/*!******************************************************************************!*\
  !*** ./src/main/webapp/app/modules/administration/user-management/index.tsx ***!
  \******************************************************************************/
/*! exports provided: default */
/***/ (function(module, __webpack_exports__, __webpack_require__) {

"use strict";
__webpack_require__.r(__webpack_exports__);
/* harmony import */ var react__WEBPACK_IMPORTED_MODULE_0__ = __webpack_require__(/*! react */ "./node_modules/react/index.js");
/* harmony import */ var react__WEBPACK_IMPORTED_MODULE_0___default = /*#__PURE__*/__webpack_require__.n(react__WEBPACK_IMPORTED_MODULE_0__);
/* harmony import */ var react_router_dom__WEBPACK_IMPORTED_MODULE_1__ = __webpack_require__(/*! react-router-dom */ "./node_modules/react-router-dom/esm/react-router-dom.js");
/* harmony import */ var app_shared_error_error_boundary_route__WEBPACK_IMPORTED_MODULE_2__ = __webpack_require__(/*! app/shared/error/error-boundary-route */ "./src/main/webapp/app/shared/error/error-boundary-route.tsx");
/* harmony import */ var _user_management__WEBPACK_IMPORTED_MODULE_3__ = __webpack_require__(/*! ./user-management */ "./src/main/webapp/app/modules/administration/user-management/user-management.tsx");
/* harmony import */ var _user_management_detail__WEBPACK_IMPORTED_MODULE_4__ = __webpack_require__(/*! ./user-management-detail */ "./src/main/webapp/app/modules/administration/user-management/user-management-detail.tsx");
/* harmony import */ var _user_management_update__WEBPACK_IMPORTED_MODULE_5__ = __webpack_require__(/*! ./user-management-update */ "./src/main/webapp/app/modules/administration/user-management/user-management-update.tsx");
/* harmony import */ var _user_management_delete_dialog__WEBPACK_IMPORTED_MODULE_6__ = __webpack_require__(/*! ./user-management-delete-dialog */ "./src/main/webapp/app/modules/administration/user-management/user-management-delete-dialog.tsx");







const Routes = ({ match }) => (react__WEBPACK_IMPORTED_MODULE_0___default.a.createElement(react__WEBPACK_IMPORTED_MODULE_0___default.a.Fragment, null,
    react__WEBPACK_IMPORTED_MODULE_0___default.a.createElement(react_router_dom__WEBPACK_IMPORTED_MODULE_1__["Switch"], null,
        react__WEBPACK_IMPORTED_MODULE_0___default.a.createElement(app_shared_error_error_boundary_route__WEBPACK_IMPORTED_MODULE_2__["default"], { exact: true, path: `${match.url}/new`, component: _user_management_update__WEBPACK_IMPORTED_MODULE_5__["default"] }),
        react__WEBPACK_IMPORTED_MODULE_0___default.a.createElement(app_shared_error_error_boundary_route__WEBPACK_IMPORTED_MODULE_2__["default"], { exact: true, path: `${match.url}/:login/edit`, component: _user_management_update__WEBPACK_IMPORTED_MODULE_5__["default"] }),
        react__WEBPACK_IMPORTED_MODULE_0___default.a.createElement(app_shared_error_error_boundary_route__WEBPACK_IMPORTED_MODULE_2__["default"], { exact: true, path: `${match.url}/:login`, component: _user_management_detail__WEBPACK_IMPORTED_MODULE_4__["default"] }),
        react__WEBPACK_IMPORTED_MODULE_0___default.a.createElement(app_shared_error_error_boundary_route__WEBPACK_IMPORTED_MODULE_2__["default"], { path: match.url, component: _user_management__WEBPACK_IMPORTED_MODULE_3__["default"] })),
    react__WEBPACK_IMPORTED_MODULE_0___default.a.createElement(app_shared_error_error_boundary_route__WEBPACK_IMPORTED_MODULE_2__["default"], { path: `${match.url}/:login/delete`, component: _user_management_delete_dialog__WEBPACK_IMPORTED_MODULE_6__["default"] })));
/* harmony default export */ __webpack_exports__["default"] = (Routes);

 void function register() { /* react-hot-loader/webpack */ var reactHotLoader = typeof reactHotLoaderGlobal !== 'undefined' ? reactHotLoaderGlobal.default : undefined; if (!reactHotLoader) { return; } /* eslint-disable camelcase, no-undef */ var webpackExports = typeof __webpack_exports__ !== 'undefined' ? __webpack_exports__ : exports; /* eslint-enable camelcase, no-undef */ if (!webpackExports) { return; } if (typeof webpackExports === 'function') { reactHotLoader.register(webpackExports, 'module.exports', "C:\\gateway\\src\\main\\webapp\\app\\modules\\administration\\user-management\\index.tsx"); return; } /* eslint-disable no-restricted-syntax */ for (var key in webpackExports) { /* eslint-enable no-restricted-syntax */ if (!Object.prototype.hasOwnProperty.call(webpackExports, key)) { continue; } var namedExport = void 0; try { namedExport = webpackExports[key]; } catch (err) { continue; } reactHotLoader.register(namedExport, key, "C:\\gateway\\src\\main\\webapp\\app\\modules\\administration\\user-management\\index.tsx"); } }(); 

/***/ }),

/***/ "./src/main/webapp/app/modules/administration/user-management/user-management-delete-dialog.tsx":
/*!******************************************************************************************************!*\
  !*** ./src/main/webapp/app/modules/administration/user-management/user-management-delete-dialog.tsx ***!
  \******************************************************************************************************/
/*! exports provided: UserManagementDeleteDialog, default */
/***/ (function(module, __webpack_exports__, __webpack_require__) {

"use strict";
__webpack_require__.r(__webpack_exports__);
/* harmony export (binding) */ __webpack_require__.d(__webpack_exports__, "UserManagementDeleteDialog", function() { return UserManagementDeleteDialog; });
/* harmony import */ var react__WEBPACK_IMPORTED_MODULE_0__ = __webpack_require__(/*! react */ "./node_modules/react/index.js");
/* harmony import */ var react__WEBPACK_IMPORTED_MODULE_0___default = /*#__PURE__*/__webpack_require__.n(react__WEBPACK_IMPORTED_MODULE_0__);
/* harmony import */ var react_redux__WEBPACK_IMPORTED_MODULE_1__ = __webpack_require__(/*! react-redux */ "./node_modules/react-redux/es/index.js");
/* harmony import */ var reactstrap__WEBPACK_IMPORTED_MODULE_2__ = __webpack_require__(/*! reactstrap */ "./node_modules/reactstrap/es/index.js");
/* harmony import */ var react_jhipster__WEBPACK_IMPORTED_MODULE_3__ = __webpack_require__(/*! react-jhipster */ "./node_modules/react-jhipster/lib/index.js");
/* harmony import */ var react_jhipster__WEBPACK_IMPORTED_MODULE_3___default = /*#__PURE__*/__webpack_require__.n(react_jhipster__WEBPACK_IMPORTED_MODULE_3__);
/* harmony import */ var _fortawesome_react_fontawesome__WEBPACK_IMPORTED_MODULE_4__ = __webpack_require__(/*! @fortawesome/react-fontawesome */ "./node_modules/@fortawesome/react-fontawesome/index.es.js");
/* harmony import */ var _user_management_reducer__WEBPACK_IMPORTED_MODULE_5__ = __webpack_require__(/*! ./user-management.reducer */ "./src/main/webapp/app/modules/administration/user-management/user-management.reducer.ts");






const UserManagementDeleteDialog = (props) => {
    Object(react__WEBPACK_IMPORTED_MODULE_0__["useEffect"])(() => {
        props.getUser(props.match.params.login);
    }, []);
    const handleClose = event => {
        event.stopPropagation();
        props.history.push('/admin/user-management');
    };
    const confirmDelete = event => {
        props.deleteUser(props.user.login);
        handleClose(event);
    };
    const { user } = props;
    return (react__WEBPACK_IMPORTED_MODULE_0___default.a.createElement(reactstrap__WEBPACK_IMPORTED_MODULE_2__["Modal"], { isOpen: true, toggle: handleClose },
        react__WEBPACK_IMPORTED_MODULE_0___default.a.createElement(reactstrap__WEBPACK_IMPORTED_MODULE_2__["ModalHeader"], { toggle: handleClose },
            react__WEBPACK_IMPORTED_MODULE_0___default.a.createElement(react_jhipster__WEBPACK_IMPORTED_MODULE_3__["Translate"], { contentKey: "entity.delete.title" }, "Confirm delete operation")),
        react__WEBPACK_IMPORTED_MODULE_0___default.a.createElement(reactstrap__WEBPACK_IMPORTED_MODULE_2__["ModalBody"], null,
            react__WEBPACK_IMPORTED_MODULE_0___default.a.createElement(react_jhipster__WEBPACK_IMPORTED_MODULE_3__["Translate"], { contentKey: "userManagement.delete.question", interpolate: { login: user.login } }, "Are you sure you want to delete this User?")),
        react__WEBPACK_IMPORTED_MODULE_0___default.a.createElement(reactstrap__WEBPACK_IMPORTED_MODULE_2__["ModalFooter"], null,
            react__WEBPACK_IMPORTED_MODULE_0___default.a.createElement(reactstrap__WEBPACK_IMPORTED_MODULE_2__["Button"], { color: "secondary", onClick: handleClose },
                react__WEBPACK_IMPORTED_MODULE_0___default.a.createElement(_fortawesome_react_fontawesome__WEBPACK_IMPORTED_MODULE_4__["FontAwesomeIcon"], { icon: "ban" }),
                "\u00A0",
                react__WEBPACK_IMPORTED_MODULE_0___default.a.createElement(react_jhipster__WEBPACK_IMPORTED_MODULE_3__["Translate"], { contentKey: "entity.action.cancel" }, "Cancel")),
            react__WEBPACK_IMPORTED_MODULE_0___default.a.createElement(reactstrap__WEBPACK_IMPORTED_MODULE_2__["Button"], { color: "danger", onClick: confirmDelete },
                react__WEBPACK_IMPORTED_MODULE_0___default.a.createElement(_fortawesome_react_fontawesome__WEBPACK_IMPORTED_MODULE_4__["FontAwesomeIcon"], { icon: "trash" }),
                "\u00A0",
                react__WEBPACK_IMPORTED_MODULE_0___default.a.createElement(react_jhipster__WEBPACK_IMPORTED_MODULE_3__["Translate"], { contentKey: "entity.action.delete" }, "Delete")))));
};
const mapStateToProps = (storeState) => ({
    user: storeState.userManagement.user,
});
const mapDispatchToProps = { getUser: _user_management_reducer__WEBPACK_IMPORTED_MODULE_5__["getUser"], deleteUser: _user_management_reducer__WEBPACK_IMPORTED_MODULE_5__["deleteUser"] };
/* harmony default export */ __webpack_exports__["default"] = (Object(react_redux__WEBPACK_IMPORTED_MODULE_1__["connect"])(mapStateToProps, mapDispatchToProps)(UserManagementDeleteDialog));

 void function register() { /* react-hot-loader/webpack */ var reactHotLoader = typeof reactHotLoaderGlobal !== 'undefined' ? reactHotLoaderGlobal.default : undefined; if (!reactHotLoader) { return; } /* eslint-disable camelcase, no-undef */ var webpackExports = typeof __webpack_exports__ !== 'undefined' ? __webpack_exports__ : exports; /* eslint-enable camelcase, no-undef */ if (!webpackExports) { return; } if (typeof webpackExports === 'function') { reactHotLoader.register(webpackExports, 'module.exports', "C:\\gateway\\src\\main\\webapp\\app\\modules\\administration\\user-management\\user-management-delete-dialog.tsx"); return; } /* eslint-disable no-restricted-syntax */ for (var key in webpackExports) { /* eslint-enable no-restricted-syntax */ if (!Object.prototype.hasOwnProperty.call(webpackExports, key)) { continue; } var namedExport = void 0; try { namedExport = webpackExports[key]; } catch (err) { continue; } reactHotLoader.register(namedExport, key, "C:\\gateway\\src\\main\\webapp\\app\\modules\\administration\\user-management\\user-management-delete-dialog.tsx"); } }(); 

/***/ }),

/***/ "./src/main/webapp/app/modules/administration/user-management/user-management-detail.tsx":
/*!***********************************************************************************************!*\
  !*** ./src/main/webapp/app/modules/administration/user-management/user-management-detail.tsx ***!
  \***********************************************************************************************/
/*! exports provided: UserManagementDetail, default */
/***/ (function(module, __webpack_exports__, __webpack_require__) {

"use strict";
__webpack_require__.r(__webpack_exports__);
/* harmony export (binding) */ __webpack_require__.d(__webpack_exports__, "UserManagementDetail", function() { return UserManagementDetail; });
/* harmony import */ var react__WEBPACK_IMPORTED_MODULE_0__ = __webpack_require__(/*! react */ "./node_modules/react/index.js");
/* harmony import */ var react__WEBPACK_IMPORTED_MODULE_0___default = /*#__PURE__*/__webpack_require__.n(react__WEBPACK_IMPORTED_MODULE_0__);
/* harmony import */ var react_redux__WEBPACK_IMPORTED_MODULE_1__ = __webpack_require__(/*! react-redux */ "./node_modules/react-redux/es/index.js");
/* harmony import */ var react_router_dom__WEBPACK_IMPORTED_MODULE_2__ = __webpack_require__(/*! react-router-dom */ "./node_modules/react-router-dom/esm/react-router-dom.js");
/* harmony import */ var reactstrap__WEBPACK_IMPORTED_MODULE_3__ = __webpack_require__(/*! reactstrap */ "./node_modules/reactstrap/es/index.js");
/* harmony import */ var react_jhipster__WEBPACK_IMPORTED_MODULE_4__ = __webpack_require__(/*! react-jhipster */ "./node_modules/react-jhipster/lib/index.js");
/* harmony import */ var react_jhipster__WEBPACK_IMPORTED_MODULE_4___default = /*#__PURE__*/__webpack_require__.n(react_jhipster__WEBPACK_IMPORTED_MODULE_4__);
/* harmony import */ var _fortawesome_react_fontawesome__WEBPACK_IMPORTED_MODULE_5__ = __webpack_require__(/*! @fortawesome/react-fontawesome */ "./node_modules/@fortawesome/react-fontawesome/index.es.js");
/* harmony import */ var app_config_constants__WEBPACK_IMPORTED_MODULE_6__ = __webpack_require__(/*! app/config/constants */ "./src/main/webapp/app/config/constants.ts");
/* harmony import */ var app_config_translation__WEBPACK_IMPORTED_MODULE_7__ = __webpack_require__(/*! app/config/translation */ "./src/main/webapp/app/config/translation.ts");
/* harmony import */ var _user_management_reducer__WEBPACK_IMPORTED_MODULE_8__ = __webpack_require__(/*! ./user-management.reducer */ "./src/main/webapp/app/modules/administration/user-management/user-management.reducer.ts");









const UserManagementDetail = (props) => {
    Object(react__WEBPACK_IMPORTED_MODULE_0__["useEffect"])(() => {
        props.getUser(props.match.params.login);
    }, []);
    const { user } = props;
    return (react__WEBPACK_IMPORTED_MODULE_0___default.a.createElement("div", null,
        react__WEBPACK_IMPORTED_MODULE_0___default.a.createElement("h2", null,
            react__WEBPACK_IMPORTED_MODULE_0___default.a.createElement(react_jhipster__WEBPACK_IMPORTED_MODULE_4__["Translate"], { contentKey: "userManagement.detail.title" }, "User"),
            " [",
            react__WEBPACK_IMPORTED_MODULE_0___default.a.createElement("b", null, user.login),
            "]"),
        react__WEBPACK_IMPORTED_MODULE_0___default.a.createElement(reactstrap__WEBPACK_IMPORTED_MODULE_3__["Row"], { size: "md" },
            react__WEBPACK_IMPORTED_MODULE_0___default.a.createElement("dl", { className: "jh-entity-details" },
                react__WEBPACK_IMPORTED_MODULE_0___default.a.createElement("dt", null,
                    react__WEBPACK_IMPORTED_MODULE_0___default.a.createElement(react_jhipster__WEBPACK_IMPORTED_MODULE_4__["Translate"], { contentKey: "userManagement.login" }, "Login")),
                react__WEBPACK_IMPORTED_MODULE_0___default.a.createElement("dd", null,
                    react__WEBPACK_IMPORTED_MODULE_0___default.a.createElement("span", null, user.login),
                    "\u00A0",
                    user.activated ? (react__WEBPACK_IMPORTED_MODULE_0___default.a.createElement(reactstrap__WEBPACK_IMPORTED_MODULE_3__["Badge"], { color: "success" },
                        react__WEBPACK_IMPORTED_MODULE_0___default.a.createElement(react_jhipster__WEBPACK_IMPORTED_MODULE_4__["Translate"], { contentKey: "userManagement.activated" }, "Activated"))) : (react__WEBPACK_IMPORTED_MODULE_0___default.a.createElement(reactstrap__WEBPACK_IMPORTED_MODULE_3__["Badge"], { color: "danger" },
                        react__WEBPACK_IMPORTED_MODULE_0___default.a.createElement(react_jhipster__WEBPACK_IMPORTED_MODULE_4__["Translate"], { contentKey: "userManagement.deactivated" }, "Deactivated")))),
                react__WEBPACK_IMPORTED_MODULE_0___default.a.createElement("dt", null,
                    react__WEBPACK_IMPORTED_MODULE_0___default.a.createElement(react_jhipster__WEBPACK_IMPORTED_MODULE_4__["Translate"], { contentKey: "userManagement.firstName" }, "First Name")),
                react__WEBPACK_IMPORTED_MODULE_0___default.a.createElement("dd", null, user.firstName),
                react__WEBPACK_IMPORTED_MODULE_0___default.a.createElement("dt", null,
                    react__WEBPACK_IMPORTED_MODULE_0___default.a.createElement(react_jhipster__WEBPACK_IMPORTED_MODULE_4__["Translate"], { contentKey: "userManagement.lastName" }, "Last Name")),
                react__WEBPACK_IMPORTED_MODULE_0___default.a.createElement("dd", null, user.lastName),
                react__WEBPACK_IMPORTED_MODULE_0___default.a.createElement("dt", null,
                    react__WEBPACK_IMPORTED_MODULE_0___default.a.createElement(react_jhipster__WEBPACK_IMPORTED_MODULE_4__["Translate"], { contentKey: "userManagement.email" }, "Email")),
                react__WEBPACK_IMPORTED_MODULE_0___default.a.createElement("dd", null, user.email),
                react__WEBPACK_IMPORTED_MODULE_0___default.a.createElement("dt", null,
                    react__WEBPACK_IMPORTED_MODULE_0___default.a.createElement(react_jhipster__WEBPACK_IMPORTED_MODULE_4__["Translate"], { contentKey: "userManagement.langKey" }, "Lang Key")),
                react__WEBPACK_IMPORTED_MODULE_0___default.a.createElement("dd", null, user.langKey ? app_config_translation__WEBPACK_IMPORTED_MODULE_7__["languages"][user.langKey].name : undefined),
                react__WEBPACK_IMPORTED_MODULE_0___default.a.createElement("dt", null,
                    react__WEBPACK_IMPORTED_MODULE_0___default.a.createElement(react_jhipster__WEBPACK_IMPORTED_MODULE_4__["Translate"], { contentKey: "userManagement.createdBy" }, "Created By")),
                react__WEBPACK_IMPORTED_MODULE_0___default.a.createElement("dd", null, user.createdBy),
                react__WEBPACK_IMPORTED_MODULE_0___default.a.createElement("dt", null,
                    react__WEBPACK_IMPORTED_MODULE_0___default.a.createElement(react_jhipster__WEBPACK_IMPORTED_MODULE_4__["Translate"], { contentKey: "userManagement.createdDate" }, "Created Date")),
                react__WEBPACK_IMPORTED_MODULE_0___default.a.createElement("dd", null, user.createdDate ? react__WEBPACK_IMPORTED_MODULE_0___default.a.createElement(react_jhipster__WEBPACK_IMPORTED_MODULE_4__["TextFormat"], { value: user.createdDate, type: "date", format: app_config_constants__WEBPACK_IMPORTED_MODULE_6__["APP_DATE_FORMAT"], blankOnInvalid: true }) : null),
                react__WEBPACK_IMPORTED_MODULE_0___default.a.createElement("dt", null,
                    react__WEBPACK_IMPORTED_MODULE_0___default.a.createElement(react_jhipster__WEBPACK_IMPORTED_MODULE_4__["Translate"], { contentKey: "userManagement.lastModifiedBy" }, "Last Modified By")),
                react__WEBPACK_IMPORTED_MODULE_0___default.a.createElement("dd", null, user.lastModifiedBy),
                react__WEBPACK_IMPORTED_MODULE_0___default.a.createElement("dt", null,
                    react__WEBPACK_IMPORTED_MODULE_0___default.a.createElement(react_jhipster__WEBPACK_IMPORTED_MODULE_4__["Translate"], { contentKey: "userManagement.lastModifiedDate" }, "Last Modified Date")),
                react__WEBPACK_IMPORTED_MODULE_0___default.a.createElement("dd", null, user.lastModifiedDate ? (react__WEBPACK_IMPORTED_MODULE_0___default.a.createElement(react_jhipster__WEBPACK_IMPORTED_MODULE_4__["TextFormat"], { value: user.lastModifiedDate, type: "date", format: app_config_constants__WEBPACK_IMPORTED_MODULE_6__["APP_DATE_FORMAT"], blankOnInvalid: true })) : null),
                react__WEBPACK_IMPORTED_MODULE_0___default.a.createElement("dt", null,
                    react__WEBPACK_IMPORTED_MODULE_0___default.a.createElement(react_jhipster__WEBPACK_IMPORTED_MODULE_4__["Translate"], { contentKey: "userManagement.profiles" }, "Profiles")),
                react__WEBPACK_IMPORTED_MODULE_0___default.a.createElement("dd", null,
                    react__WEBPACK_IMPORTED_MODULE_0___default.a.createElement("ul", { className: "list-unstyled" }, user.authorities
                        ? user.authorities.map((authority, i) => (react__WEBPACK_IMPORTED_MODULE_0___default.a.createElement("li", { key: `user-auth-${i}` },
                            react__WEBPACK_IMPORTED_MODULE_0___default.a.createElement(reactstrap__WEBPACK_IMPORTED_MODULE_3__["Badge"], { color: "info" }, authority))))
                        : null)))),
        react__WEBPACK_IMPORTED_MODULE_0___default.a.createElement(reactstrap__WEBPACK_IMPORTED_MODULE_3__["Button"], { tag: react_router_dom__WEBPACK_IMPORTED_MODULE_2__["Link"], to: "/admin/user-management", replace: true, color: "info" },
            react__WEBPACK_IMPORTED_MODULE_0___default.a.createElement(_fortawesome_react_fontawesome__WEBPACK_IMPORTED_MODULE_5__["FontAwesomeIcon"], { icon: "arrow-left" }),
            ' ',
            react__WEBPACK_IMPORTED_MODULE_0___default.a.createElement("span", { className: "d-none d-md-inline" },
                react__WEBPACK_IMPORTED_MODULE_0___default.a.createElement(react_jhipster__WEBPACK_IMPORTED_MODULE_4__["Translate"], { contentKey: "entity.action.back" }, "Back")))));
};
const mapStateToProps = (storeState) => ({
    user: storeState.userManagement.user,
});
const mapDispatchToProps = { getUser: _user_management_reducer__WEBPACK_IMPORTED_MODULE_8__["getUser"] };
/* harmony default export */ __webpack_exports__["default"] = (Object(react_redux__WEBPACK_IMPORTED_MODULE_1__["connect"])(mapStateToProps, mapDispatchToProps)(UserManagementDetail));

 void function register() { /* react-hot-loader/webpack */ var reactHotLoader = typeof reactHotLoaderGlobal !== 'undefined' ? reactHotLoaderGlobal.default : undefined; if (!reactHotLoader) { return; } /* eslint-disable camelcase, no-undef */ var webpackExports = typeof __webpack_exports__ !== 'undefined' ? __webpack_exports__ : exports; /* eslint-enable camelcase, no-undef */ if (!webpackExports) { return; } if (typeof webpackExports === 'function') { reactHotLoader.register(webpackExports, 'module.exports', "C:\\gateway\\src\\main\\webapp\\app\\modules\\administration\\user-management\\user-management-detail.tsx"); return; } /* eslint-disable no-restricted-syntax */ for (var key in webpackExports) { /* eslint-enable no-restricted-syntax */ if (!Object.prototype.hasOwnProperty.call(webpackExports, key)) { continue; } var namedExport = void 0; try { namedExport = webpackExports[key]; } catch (err) { continue; } reactHotLoader.register(namedExport, key, "C:\\gateway\\src\\main\\webapp\\app\\modules\\administration\\user-management\\user-management-detail.tsx"); } }(); 

/***/ }),

/***/ "./src/main/webapp/app/modules/administration/user-management/user-management-update.tsx":
/*!***********************************************************************************************!*\
  !*** ./src/main/webapp/app/modules/administration/user-management/user-management-update.tsx ***!
  \***********************************************************************************************/
/*! exports provided: UserManagementUpdate, default */
/***/ (function(module, __webpack_exports__, __webpack_require__) {

"use strict";
__webpack_require__.r(__webpack_exports__);
/* harmony export (binding) */ __webpack_require__.d(__webpack_exports__, "UserManagementUpdate", function() { return UserManagementUpdate; });
/* harmony import */ var react__WEBPACK_IMPORTED_MODULE_0__ = __webpack_require__(/*! react */ "./node_modules/react/index.js");
/* harmony import */ var react__WEBPACK_IMPORTED_MODULE_0___default = /*#__PURE__*/__webpack_require__.n(react__WEBPACK_IMPORTED_MODULE_0__);
/* harmony import */ var react_redux__WEBPACK_IMPORTED_MODULE_1__ = __webpack_require__(/*! react-redux */ "./node_modules/react-redux/es/index.js");
/* harmony import */ var react_router_dom__WEBPACK_IMPORTED_MODULE_2__ = __webpack_require__(/*! react-router-dom */ "./node_modules/react-router-dom/esm/react-router-dom.js");
/* harmony import */ var reactstrap__WEBPACK_IMPORTED_MODULE_3__ = __webpack_require__(/*! reactstrap */ "./node_modules/reactstrap/es/index.js");
/* harmony import */ var availity_reactstrap_validation__WEBPACK_IMPORTED_MODULE_4__ = __webpack_require__(/*! availity-reactstrap-validation */ "./node_modules/availity-reactstrap-validation/lib/index.js");
/* harmony import */ var availity_reactstrap_validation__WEBPACK_IMPORTED_MODULE_4___default = /*#__PURE__*/__webpack_require__.n(availity_reactstrap_validation__WEBPACK_IMPORTED_MODULE_4__);
/* harmony import */ var react_jhipster__WEBPACK_IMPORTED_MODULE_5__ = __webpack_require__(/*! react-jhipster */ "./node_modules/react-jhipster/lib/index.js");
/* harmony import */ var react_jhipster__WEBPACK_IMPORTED_MODULE_5___default = /*#__PURE__*/__webpack_require__.n(react_jhipster__WEBPACK_IMPORTED_MODULE_5__);
/* harmony import */ var _fortawesome_react_fontawesome__WEBPACK_IMPORTED_MODULE_6__ = __webpack_require__(/*! @fortawesome/react-fontawesome */ "./node_modules/@fortawesome/react-fontawesome/index.es.js");
/* harmony import */ var app_config_translation__WEBPACK_IMPORTED_MODULE_7__ = __webpack_require__(/*! app/config/translation */ "./src/main/webapp/app/config/translation.ts");
/* harmony import */ var _user_management_reducer__WEBPACK_IMPORTED_MODULE_8__ = __webpack_require__(/*! ./user-management.reducer */ "./src/main/webapp/app/modules/administration/user-management/user-management.reducer.ts");









const UserManagementUpdate = (props) => {
    const [isNew, setIsNew] = Object(react__WEBPACK_IMPORTED_MODULE_0__["useState"])(!props.match.params || !props.match.params.login);
    Object(react__WEBPACK_IMPORTED_MODULE_0__["useEffect"])(() => {
        if (isNew) {
            props.reset();
        }
        else {
            props.getUser(props.match.params.login);
        }
        props.getRoles();
        return () => {
            props.reset();
        };
    }, []);
    const handleClose = () => {
        props.history.push('/admin/user-management');
    };
    const saveUser = (event, values) => {
        if (isNew) {
            props.createUser(values);
        }
        else {
            props.updateUser(values);
        }
        handleClose();
    };
    const isInvalid = false;
    const { user, loading, updating, roles } = props;
    return (react__WEBPACK_IMPORTED_MODULE_0___default.a.createElement("div", null,
        react__WEBPACK_IMPORTED_MODULE_0___default.a.createElement(reactstrap__WEBPACK_IMPORTED_MODULE_3__["Row"], { className: "justify-content-center" },
            react__WEBPACK_IMPORTED_MODULE_0___default.a.createElement(reactstrap__WEBPACK_IMPORTED_MODULE_3__["Col"], { md: "8" },
                react__WEBPACK_IMPORTED_MODULE_0___default.a.createElement("h1", null,
                    react__WEBPACK_IMPORTED_MODULE_0___default.a.createElement(react_jhipster__WEBPACK_IMPORTED_MODULE_5__["Translate"], { contentKey: "userManagement.home.createOrEditLabel" }, "Create or edit a User")))),
        react__WEBPACK_IMPORTED_MODULE_0___default.a.createElement(reactstrap__WEBPACK_IMPORTED_MODULE_3__["Row"], { className: "justify-content-center" },
            react__WEBPACK_IMPORTED_MODULE_0___default.a.createElement(reactstrap__WEBPACK_IMPORTED_MODULE_3__["Col"], { md: "8" }, loading ? (react__WEBPACK_IMPORTED_MODULE_0___default.a.createElement("p", null, "Loading...")) : (react__WEBPACK_IMPORTED_MODULE_0___default.a.createElement(availity_reactstrap_validation__WEBPACK_IMPORTED_MODULE_4__["AvForm"], { onValidSubmit: saveUser },
                user.id ? (react__WEBPACK_IMPORTED_MODULE_0___default.a.createElement(availity_reactstrap_validation__WEBPACK_IMPORTED_MODULE_4__["AvGroup"], null,
                    react__WEBPACK_IMPORTED_MODULE_0___default.a.createElement(reactstrap__WEBPACK_IMPORTED_MODULE_3__["Label"], { for: "id" },
                        react__WEBPACK_IMPORTED_MODULE_0___default.a.createElement(react_jhipster__WEBPACK_IMPORTED_MODULE_5__["Translate"], { contentKey: "global.field.id" }, "ID")),
                    react__WEBPACK_IMPORTED_MODULE_0___default.a.createElement(availity_reactstrap_validation__WEBPACK_IMPORTED_MODULE_4__["AvField"], { type: "text", className: "form-control", name: "id", required: true, readOnly: true, value: user.id }))) : null,
                react__WEBPACK_IMPORTED_MODULE_0___default.a.createElement(availity_reactstrap_validation__WEBPACK_IMPORTED_MODULE_4__["AvGroup"], null,
                    react__WEBPACK_IMPORTED_MODULE_0___default.a.createElement(reactstrap__WEBPACK_IMPORTED_MODULE_3__["Label"], { for: "login" },
                        react__WEBPACK_IMPORTED_MODULE_0___default.a.createElement(react_jhipster__WEBPACK_IMPORTED_MODULE_5__["Translate"], { contentKey: "userManagement.login" }, "Login")),
                    react__WEBPACK_IMPORTED_MODULE_0___default.a.createElement(availity_reactstrap_validation__WEBPACK_IMPORTED_MODULE_4__["AvField"], { type: "text", className: "form-control", name: "login", validate: {
                            required: {
                                value: true,
                                errorMessage: Object(react_jhipster__WEBPACK_IMPORTED_MODULE_5__["translate"])('register.messages.validate.login.required'),
                            },
                            pattern: {
                                value: '^[a-zA-Z0-9!$&*+=?^_`{|}~.-]+@[a-zA-Z0-9-]+(?:\\.[a-zA-Z0-9-]+)*$|^[_.@A-Za-z0-9-]+$',
                                errorMessage: Object(react_jhipster__WEBPACK_IMPORTED_MODULE_5__["translate"])('register.messages.validate.login.pattern'),
                            },
                            minLength: {
                                value: 1,
                                errorMessage: Object(react_jhipster__WEBPACK_IMPORTED_MODULE_5__["translate"])('register.messages.validate.login.minlength'),
                            },
                            maxLength: {
                                value: 50,
                                errorMessage: Object(react_jhipster__WEBPACK_IMPORTED_MODULE_5__["translate"])('register.messages.validate.login.maxlength'),
                            },
                        }, value: user.login })),
                react__WEBPACK_IMPORTED_MODULE_0___default.a.createElement(availity_reactstrap_validation__WEBPACK_IMPORTED_MODULE_4__["AvGroup"], null,
                    react__WEBPACK_IMPORTED_MODULE_0___default.a.createElement(reactstrap__WEBPACK_IMPORTED_MODULE_3__["Label"], { for: "firstName" },
                        react__WEBPACK_IMPORTED_MODULE_0___default.a.createElement(react_jhipster__WEBPACK_IMPORTED_MODULE_5__["Translate"], { contentKey: "userManagement.firstName" }, "First Name")),
                    react__WEBPACK_IMPORTED_MODULE_0___default.a.createElement(availity_reactstrap_validation__WEBPACK_IMPORTED_MODULE_4__["AvField"], { type: "text", className: "form-control", name: "firstName", validate: {
                            maxLength: {
                                value: 50,
                                errorMessage: Object(react_jhipster__WEBPACK_IMPORTED_MODULE_5__["translate"])('entity.validation.maxlength', { max: 50 }),
                            },
                        }, value: user.firstName })),
                react__WEBPACK_IMPORTED_MODULE_0___default.a.createElement(availity_reactstrap_validation__WEBPACK_IMPORTED_MODULE_4__["AvGroup"], null,
                    react__WEBPACK_IMPORTED_MODULE_0___default.a.createElement(reactstrap__WEBPACK_IMPORTED_MODULE_3__["Label"], { for: "lastName" },
                        react__WEBPACK_IMPORTED_MODULE_0___default.a.createElement(react_jhipster__WEBPACK_IMPORTED_MODULE_5__["Translate"], { contentKey: "userManagement.lastName" }, "Last Name")),
                    react__WEBPACK_IMPORTED_MODULE_0___default.a.createElement(availity_reactstrap_validation__WEBPACK_IMPORTED_MODULE_4__["AvField"], { type: "text", className: "form-control", name: "lastName", validate: {
                            maxLength: {
                                value: 50,
                                errorMessage: Object(react_jhipster__WEBPACK_IMPORTED_MODULE_5__["translate"])('entity.validation.maxlength', { max: 50 }),
                            },
                        }, value: user.lastName }),
                    react__WEBPACK_IMPORTED_MODULE_0___default.a.createElement(availity_reactstrap_validation__WEBPACK_IMPORTED_MODULE_4__["AvFeedback"], null, "This field cannot be longer than 50 characters.")),
                react__WEBPACK_IMPORTED_MODULE_0___default.a.createElement(availity_reactstrap_validation__WEBPACK_IMPORTED_MODULE_4__["AvGroup"], null,
                    react__WEBPACK_IMPORTED_MODULE_0___default.a.createElement(availity_reactstrap_validation__WEBPACK_IMPORTED_MODULE_4__["AvField"], { name: "email", label: Object(react_jhipster__WEBPACK_IMPORTED_MODULE_5__["translate"])('global.form.email.label'), placeholder: Object(react_jhipster__WEBPACK_IMPORTED_MODULE_5__["translate"])('global.form.email.placeholder'), type: "email", validate: {
                            required: {
                                value: true,
                                errorMessage: Object(react_jhipster__WEBPACK_IMPORTED_MODULE_5__["translate"])('global.messages.validate.email.required'),
                            },
                            email: {
                                errorMessage: Object(react_jhipster__WEBPACK_IMPORTED_MODULE_5__["translate"])('global.messages.validate.email.invalid'),
                            },
                            minLength: {
                                value: 5,
                                errorMessage: Object(react_jhipster__WEBPACK_IMPORTED_MODULE_5__["translate"])('global.messages.validate.email.minlength'),
                            },
                            maxLength: {
                                value: 254,
                                errorMessage: Object(react_jhipster__WEBPACK_IMPORTED_MODULE_5__["translate"])('global.messages.validate.email.maxlength'),
                            },
                        }, value: user.email })),
                react__WEBPACK_IMPORTED_MODULE_0___default.a.createElement(availity_reactstrap_validation__WEBPACK_IMPORTED_MODULE_4__["AvGroup"], { check: true },
                    react__WEBPACK_IMPORTED_MODULE_0___default.a.createElement(reactstrap__WEBPACK_IMPORTED_MODULE_3__["Label"], null,
                        react__WEBPACK_IMPORTED_MODULE_0___default.a.createElement(availity_reactstrap_validation__WEBPACK_IMPORTED_MODULE_4__["AvInput"], { type: "checkbox", name: "activated", value: user.activated, checked: user.activated, disabled: !user.id }),
                        ' ',
                        react__WEBPACK_IMPORTED_MODULE_0___default.a.createElement(react_jhipster__WEBPACK_IMPORTED_MODULE_5__["Translate"], { contentKey: "userManagement.activated" }, "Activated"))),
                react__WEBPACK_IMPORTED_MODULE_0___default.a.createElement(availity_reactstrap_validation__WEBPACK_IMPORTED_MODULE_4__["AvGroup"], null,
                    react__WEBPACK_IMPORTED_MODULE_0___default.a.createElement(reactstrap__WEBPACK_IMPORTED_MODULE_3__["Label"], { for: "langKey" },
                        react__WEBPACK_IMPORTED_MODULE_0___default.a.createElement(react_jhipster__WEBPACK_IMPORTED_MODULE_5__["Translate"], { contentKey: "userManagement.langKey" }, "Language Key")),
                    react__WEBPACK_IMPORTED_MODULE_0___default.a.createElement(availity_reactstrap_validation__WEBPACK_IMPORTED_MODULE_4__["AvField"], { type: "select", className: "form-control", name: "langKey", value: user.langKey || app_config_translation__WEBPACK_IMPORTED_MODULE_7__["locales"][0] }, app_config_translation__WEBPACK_IMPORTED_MODULE_7__["locales"].map(locale => (react__WEBPACK_IMPORTED_MODULE_0___default.a.createElement("option", { value: locale, key: locale }, app_config_translation__WEBPACK_IMPORTED_MODULE_7__["languages"][locale].name))))),
                react__WEBPACK_IMPORTED_MODULE_0___default.a.createElement(availity_reactstrap_validation__WEBPACK_IMPORTED_MODULE_4__["AvGroup"], null,
                    react__WEBPACK_IMPORTED_MODULE_0___default.a.createElement(reactstrap__WEBPACK_IMPORTED_MODULE_3__["Label"], { for: "authorities" },
                        react__WEBPACK_IMPORTED_MODULE_0___default.a.createElement(react_jhipster__WEBPACK_IMPORTED_MODULE_5__["Translate"], { contentKey: "userManagement.profiles" }, "Profiles")),
                    react__WEBPACK_IMPORTED_MODULE_0___default.a.createElement(availity_reactstrap_validation__WEBPACK_IMPORTED_MODULE_4__["AvInput"], { type: "select", className: "form-control", name: "authorities", value: user.authorities, multiple: true }, roles.map(role => (react__WEBPACK_IMPORTED_MODULE_0___default.a.createElement("option", { value: role, key: role }, role))))),
                react__WEBPACK_IMPORTED_MODULE_0___default.a.createElement(reactstrap__WEBPACK_IMPORTED_MODULE_3__["Button"], { tag: react_router_dom__WEBPACK_IMPORTED_MODULE_2__["Link"], to: "/admin/user-management", replace: true, color: "info" },
                    react__WEBPACK_IMPORTED_MODULE_0___default.a.createElement(_fortawesome_react_fontawesome__WEBPACK_IMPORTED_MODULE_6__["FontAwesomeIcon"], { icon: "arrow-left" }),
                    "\u00A0",
                    react__WEBPACK_IMPORTED_MODULE_0___default.a.createElement("span", { className: "d-none d-md-inline" },
                        react__WEBPACK_IMPORTED_MODULE_0___default.a.createElement(react_jhipster__WEBPACK_IMPORTED_MODULE_5__["Translate"], { contentKey: "entity.action.back" }, "Back"))),
                "\u00A0",
                react__WEBPACK_IMPORTED_MODULE_0___default.a.createElement(reactstrap__WEBPACK_IMPORTED_MODULE_3__["Button"], { color: "primary", type: "submit", disabled: isInvalid || updating },
                    react__WEBPACK_IMPORTED_MODULE_0___default.a.createElement(_fortawesome_react_fontawesome__WEBPACK_IMPORTED_MODULE_6__["FontAwesomeIcon"], { icon: "save" }),
                    "\u00A0",
                    react__WEBPACK_IMPORTED_MODULE_0___default.a.createElement(react_jhipster__WEBPACK_IMPORTED_MODULE_5__["Translate"], { contentKey: "entity.action.save" }, "Save"))))))));
};
const mapStateToProps = (storeState) => ({
    user: storeState.userManagement.user,
    roles: storeState.userManagement.authorities,
    loading: storeState.userManagement.loading,
    updating: storeState.userManagement.updating,
});
const mapDispatchToProps = { getUser: _user_management_reducer__WEBPACK_IMPORTED_MODULE_8__["getUser"], getRoles: _user_management_reducer__WEBPACK_IMPORTED_MODULE_8__["getRoles"], updateUser: _user_management_reducer__WEBPACK_IMPORTED_MODULE_8__["updateUser"], createUser: _user_management_reducer__WEBPACK_IMPORTED_MODULE_8__["createUser"], reset: _user_management_reducer__WEBPACK_IMPORTED_MODULE_8__["reset"] };
/* harmony default export */ __webpack_exports__["default"] = (Object(react_redux__WEBPACK_IMPORTED_MODULE_1__["connect"])(mapStateToProps, mapDispatchToProps)(UserManagementUpdate));

 void function register() { /* react-hot-loader/webpack */ var reactHotLoader = typeof reactHotLoaderGlobal !== 'undefined' ? reactHotLoaderGlobal.default : undefined; if (!reactHotLoader) { return; } /* eslint-disable camelcase, no-undef */ var webpackExports = typeof __webpack_exports__ !== 'undefined' ? __webpack_exports__ : exports; /* eslint-enable camelcase, no-undef */ if (!webpackExports) { return; } if (typeof webpackExports === 'function') { reactHotLoader.register(webpackExports, 'module.exports', "C:\\gateway\\src\\main\\webapp\\app\\modules\\administration\\user-management\\user-management-update.tsx"); return; } /* eslint-disable no-restricted-syntax */ for (var key in webpackExports) { /* eslint-enable no-restricted-syntax */ if (!Object.prototype.hasOwnProperty.call(webpackExports, key)) { continue; } var namedExport = void 0; try { namedExport = webpackExports[key]; } catch (err) { continue; } reactHotLoader.register(namedExport, key, "C:\\gateway\\src\\main\\webapp\\app\\modules\\administration\\user-management\\user-management-update.tsx"); } }(); 

/***/ }),

/***/ "./src/main/webapp/app/modules/administration/user-management/user-management.tsx":
/*!****************************************************************************************!*\
  !*** ./src/main/webapp/app/modules/administration/user-management/user-management.tsx ***!
  \****************************************************************************************/
/*! exports provided: UserManagement, default */
/***/ (function(module, __webpack_exports__, __webpack_require__) {

"use strict";
__webpack_require__.r(__webpack_exports__);
/* harmony export (binding) */ __webpack_require__.d(__webpack_exports__, "UserManagement", function() { return UserManagement; });
/* harmony import */ var react__WEBPACK_IMPORTED_MODULE_0__ = __webpack_require__(/*! react */ "./node_modules/react/index.js");
/* harmony import */ var react__WEBPACK_IMPORTED_MODULE_0___default = /*#__PURE__*/__webpack_require__.n(react__WEBPACK_IMPORTED_MODULE_0__);
/* harmony import */ var react_redux__WEBPACK_IMPORTED_MODULE_1__ = __webpack_require__(/*! react-redux */ "./node_modules/react-redux/es/index.js");
/* harmony import */ var react_router_dom__WEBPACK_IMPORTED_MODULE_2__ = __webpack_require__(/*! react-router-dom */ "./node_modules/react-router-dom/esm/react-router-dom.js");
/* harmony import */ var reactstrap__WEBPACK_IMPORTED_MODULE_3__ = __webpack_require__(/*! reactstrap */ "./node_modules/reactstrap/es/index.js");
/* harmony import */ var react_jhipster__WEBPACK_IMPORTED_MODULE_4__ = __webpack_require__(/*! react-jhipster */ "./node_modules/react-jhipster/lib/index.js");
/* harmony import */ var react_jhipster__WEBPACK_IMPORTED_MODULE_4___default = /*#__PURE__*/__webpack_require__.n(react_jhipster__WEBPACK_IMPORTED_MODULE_4__);
/* harmony import */ var _fortawesome_react_fontawesome__WEBPACK_IMPORTED_MODULE_5__ = __webpack_require__(/*! @fortawesome/react-fontawesome */ "./node_modules/@fortawesome/react-fontawesome/index.es.js");
/* harmony import */ var app_config_constants__WEBPACK_IMPORTED_MODULE_6__ = __webpack_require__(/*! app/config/constants */ "./src/main/webapp/app/config/constants.ts");
/* harmony import */ var app_shared_util_pagination_constants__WEBPACK_IMPORTED_MODULE_7__ = __webpack_require__(/*! app/shared/util/pagination.constants */ "./src/main/webapp/app/shared/util/pagination.constants.ts");
/* harmony import */ var app_shared_util_entity_utils__WEBPACK_IMPORTED_MODULE_8__ = __webpack_require__(/*! app/shared/util/entity-utils */ "./src/main/webapp/app/shared/util/entity-utils.ts");
/* harmony import */ var _user_management_reducer__WEBPACK_IMPORTED_MODULE_9__ = __webpack_require__(/*! ./user-management.reducer */ "./src/main/webapp/app/modules/administration/user-management/user-management.reducer.ts");










const UserManagement = (props) => {
    const [pagination, setPagination] = Object(react__WEBPACK_IMPORTED_MODULE_0__["useState"])(Object(app_shared_util_entity_utils__WEBPACK_IMPORTED_MODULE_8__["overridePaginationStateWithQueryParams"])(Object(react_jhipster__WEBPACK_IMPORTED_MODULE_4__["getSortState"])(props.location, app_shared_util_pagination_constants__WEBPACK_IMPORTED_MODULE_7__["ITEMS_PER_PAGE"]), props.location.search));
    Object(react__WEBPACK_IMPORTED_MODULE_0__["useEffect"])(() => {
        props.getUsers(pagination.activePage - 1, pagination.itemsPerPage, `${pagination.sort},${pagination.order}`);
        const endURL = `?page=${pagination.activePage}&sort=${pagination.sort},${pagination.order}`;
        if (props.location.search !== endURL) {
            props.history.push(`${props.location.pathname}${endURL}`);
        }
    }, [pagination.activePage, pagination.order, pagination.sort]);
    Object(react__WEBPACK_IMPORTED_MODULE_0__["useEffect"])(() => {
        const params = new URLSearchParams(props.location.search);
        const page = params.get('page');
        const sort = params.get('sort');
        if (page && sort) {
            const sortSplit = sort.split(',');
            setPagination(Object.assign(Object.assign({}, pagination), { activePage: +page, sort: sortSplit[0], order: sortSplit[1] }));
        }
    }, [props.location.search]);
    const sort = p => () => setPagination(Object.assign(Object.assign({}, pagination), { order: pagination.order === 'asc' ? 'desc' : 'asc', sort: p }));
    const handlePagination = currentPage => setPagination(Object.assign(Object.assign({}, pagination), { activePage: currentPage }));
    const toggleActive = user => () => props.updateUser(Object.assign(Object.assign({}, user), { activated: !user.activated }));
    const { users, account, match, totalItems } = props;
    return (react__WEBPACK_IMPORTED_MODULE_0___default.a.createElement("div", null,
        react__WEBPACK_IMPORTED_MODULE_0___default.a.createElement("h2", { id: "user-management-page-heading" },
            react__WEBPACK_IMPORTED_MODULE_0___default.a.createElement(react_jhipster__WEBPACK_IMPORTED_MODULE_4__["Translate"], { contentKey: "userManagement.home.title" }, "Users"),
            react__WEBPACK_IMPORTED_MODULE_0___default.a.createElement(react_router_dom__WEBPACK_IMPORTED_MODULE_2__["Link"], { to: `${match.url}/new`, className: "btn btn-primary float-right jh-create-entity" },
                react__WEBPACK_IMPORTED_MODULE_0___default.a.createElement(_fortawesome_react_fontawesome__WEBPACK_IMPORTED_MODULE_5__["FontAwesomeIcon"], { icon: "plus" }),
                " ",
                react__WEBPACK_IMPORTED_MODULE_0___default.a.createElement(react_jhipster__WEBPACK_IMPORTED_MODULE_4__["Translate"], { contentKey: "userManagement.home.createLabel" }, "Create a new user"))),
        react__WEBPACK_IMPORTED_MODULE_0___default.a.createElement(reactstrap__WEBPACK_IMPORTED_MODULE_3__["Table"], { responsive: true, striped: true },
            react__WEBPACK_IMPORTED_MODULE_0___default.a.createElement("thead", null,
                react__WEBPACK_IMPORTED_MODULE_0___default.a.createElement("tr", null,
                    react__WEBPACK_IMPORTED_MODULE_0___default.a.createElement("th", { className: "hand", onClick: sort('id') },
                        react__WEBPACK_IMPORTED_MODULE_0___default.a.createElement(react_jhipster__WEBPACK_IMPORTED_MODULE_4__["Translate"], { contentKey: "global.field.id" }, "ID"),
                        react__WEBPACK_IMPORTED_MODULE_0___default.a.createElement(_fortawesome_react_fontawesome__WEBPACK_IMPORTED_MODULE_5__["FontAwesomeIcon"], { icon: "sort" })),
                    react__WEBPACK_IMPORTED_MODULE_0___default.a.createElement("th", { className: "hand", onClick: sort('login') },
                        react__WEBPACK_IMPORTED_MODULE_0___default.a.createElement(react_jhipster__WEBPACK_IMPORTED_MODULE_4__["Translate"], { contentKey: "userManagement.login" }, "Login"),
                        react__WEBPACK_IMPORTED_MODULE_0___default.a.createElement(_fortawesome_react_fontawesome__WEBPACK_IMPORTED_MODULE_5__["FontAwesomeIcon"], { icon: "sort" })),
                    react__WEBPACK_IMPORTED_MODULE_0___default.a.createElement("th", { className: "hand", onClick: sort('email') },
                        react__WEBPACK_IMPORTED_MODULE_0___default.a.createElement(react_jhipster__WEBPACK_IMPORTED_MODULE_4__["Translate"], { contentKey: "userManagement.email" }, "Email"),
                        react__WEBPACK_IMPORTED_MODULE_0___default.a.createElement(_fortawesome_react_fontawesome__WEBPACK_IMPORTED_MODULE_5__["FontAwesomeIcon"], { icon: "sort" })),
                    react__WEBPACK_IMPORTED_MODULE_0___default.a.createElement("th", null),
                    react__WEBPACK_IMPORTED_MODULE_0___default.a.createElement("th", { className: "hand", onClick: sort('langKey') },
                        react__WEBPACK_IMPORTED_MODULE_0___default.a.createElement(react_jhipster__WEBPACK_IMPORTED_MODULE_4__["Translate"], { contentKey: "userManagement.langKey" }, "Lang Key"),
                        react__WEBPACK_IMPORTED_MODULE_0___default.a.createElement(_fortawesome_react_fontawesome__WEBPACK_IMPORTED_MODULE_5__["FontAwesomeIcon"], { icon: "sort" })),
                    react__WEBPACK_IMPORTED_MODULE_0___default.a.createElement("th", null,
                        react__WEBPACK_IMPORTED_MODULE_0___default.a.createElement(react_jhipster__WEBPACK_IMPORTED_MODULE_4__["Translate"], { contentKey: "userManagement.profiles" }, "Profiles")),
                    react__WEBPACK_IMPORTED_MODULE_0___default.a.createElement("th", { className: "hand", onClick: sort('createdDate') },
                        react__WEBPACK_IMPORTED_MODULE_0___default.a.createElement(react_jhipster__WEBPACK_IMPORTED_MODULE_4__["Translate"], { contentKey: "userManagement.createdDate" }, "Created Date"),
                        react__WEBPACK_IMPORTED_MODULE_0___default.a.createElement(_fortawesome_react_fontawesome__WEBPACK_IMPORTED_MODULE_5__["FontAwesomeIcon"], { icon: "sort" })),
                    react__WEBPACK_IMPORTED_MODULE_0___default.a.createElement("th", { className: "hand", onClick: sort('lastModifiedBy') },
                        react__WEBPACK_IMPORTED_MODULE_0___default.a.createElement(react_jhipster__WEBPACK_IMPORTED_MODULE_4__["Translate"], { contentKey: "userManagement.lastModifiedBy" }, "Last Modified By"),
                        react__WEBPACK_IMPORTED_MODULE_0___default.a.createElement(_fortawesome_react_fontawesome__WEBPACK_IMPORTED_MODULE_5__["FontAwesomeIcon"], { icon: "sort" })),
                    react__WEBPACK_IMPORTED_MODULE_0___default.a.createElement("th", { id: "modified-date-sort", className: "hand", onClick: sort('lastModifiedDate') },
                        react__WEBPACK_IMPORTED_MODULE_0___default.a.createElement(react_jhipster__WEBPACK_IMPORTED_MODULE_4__["Translate"], { contentKey: "userManagement.lastModifiedDate" }, "Last Modified Date"),
                        react__WEBPACK_IMPORTED_MODULE_0___default.a.createElement(_fortawesome_react_fontawesome__WEBPACK_IMPORTED_MODULE_5__["FontAwesomeIcon"], { icon: "sort" })),
                    react__WEBPACK_IMPORTED_MODULE_0___default.a.createElement("th", null))),
            react__WEBPACK_IMPORTED_MODULE_0___default.a.createElement("tbody", null, users.map((user, i) => (react__WEBPACK_IMPORTED_MODULE_0___default.a.createElement("tr", { id: user.login, key: `user-${i}` },
                react__WEBPACK_IMPORTED_MODULE_0___default.a.createElement("td", null,
                    react__WEBPACK_IMPORTED_MODULE_0___default.a.createElement(reactstrap__WEBPACK_IMPORTED_MODULE_3__["Button"], { tag: react_router_dom__WEBPACK_IMPORTED_MODULE_2__["Link"], to: `${match.url}/${user.login}`, color: "link", size: "sm" }, user.id)),
                react__WEBPACK_IMPORTED_MODULE_0___default.a.createElement("td", null, user.login),
                react__WEBPACK_IMPORTED_MODULE_0___default.a.createElement("td", null, user.email),
                react__WEBPACK_IMPORTED_MODULE_0___default.a.createElement("td", null, user.activated ? (react__WEBPACK_IMPORTED_MODULE_0___default.a.createElement(reactstrap__WEBPACK_IMPORTED_MODULE_3__["Button"], { color: "success", onClick: toggleActive(user) },
                    react__WEBPACK_IMPORTED_MODULE_0___default.a.createElement(react_jhipster__WEBPACK_IMPORTED_MODULE_4__["Translate"], { contentKey: "userManagement.activated" }, "Activated"))) : (react__WEBPACK_IMPORTED_MODULE_0___default.a.createElement(reactstrap__WEBPACK_IMPORTED_MODULE_3__["Button"], { color: "danger", onClick: toggleActive(user) },
                    react__WEBPACK_IMPORTED_MODULE_0___default.a.createElement(react_jhipster__WEBPACK_IMPORTED_MODULE_4__["Translate"], { contentKey: "userManagement.deactivated" }, "Deactivated")))),
                react__WEBPACK_IMPORTED_MODULE_0___default.a.createElement("td", null, user.langKey),
                react__WEBPACK_IMPORTED_MODULE_0___default.a.createElement("td", null, user.authorities
                    ? user.authorities.map((authority, j) => (react__WEBPACK_IMPORTED_MODULE_0___default.a.createElement("div", { key: `user-auth-${i}-${j}` },
                        react__WEBPACK_IMPORTED_MODULE_0___default.a.createElement(reactstrap__WEBPACK_IMPORTED_MODULE_3__["Badge"], { color: "info" }, authority))))
                    : null),
                react__WEBPACK_IMPORTED_MODULE_0___default.a.createElement("td", null, user.createdDate ? react__WEBPACK_IMPORTED_MODULE_0___default.a.createElement(react_jhipster__WEBPACK_IMPORTED_MODULE_4__["TextFormat"], { value: user.createdDate, type: "date", format: app_config_constants__WEBPACK_IMPORTED_MODULE_6__["APP_DATE_FORMAT"], blankOnInvalid: true }) : null),
                react__WEBPACK_IMPORTED_MODULE_0___default.a.createElement("td", null, user.lastModifiedBy),
                react__WEBPACK_IMPORTED_MODULE_0___default.a.createElement("td", null, user.lastModifiedDate ? (react__WEBPACK_IMPORTED_MODULE_0___default.a.createElement(react_jhipster__WEBPACK_IMPORTED_MODULE_4__["TextFormat"], { value: user.lastModifiedDate, type: "date", format: app_config_constants__WEBPACK_IMPORTED_MODULE_6__["APP_DATE_FORMAT"], blankOnInvalid: true })) : null),
                react__WEBPACK_IMPORTED_MODULE_0___default.a.createElement("td", { className: "text-right" },
                    react__WEBPACK_IMPORTED_MODULE_0___default.a.createElement("div", { className: "btn-group flex-btn-group-container" },
                        react__WEBPACK_IMPORTED_MODULE_0___default.a.createElement(reactstrap__WEBPACK_IMPORTED_MODULE_3__["Button"], { tag: react_router_dom__WEBPACK_IMPORTED_MODULE_2__["Link"], to: `${match.url}/${user.login}`, color: "info", size: "sm" },
                            react__WEBPACK_IMPORTED_MODULE_0___default.a.createElement(_fortawesome_react_fontawesome__WEBPACK_IMPORTED_MODULE_5__["FontAwesomeIcon"], { icon: "eye" }),
                            ' ',
                            react__WEBPACK_IMPORTED_MODULE_0___default.a.createElement("span", { className: "d-none d-md-inline" },
                                react__WEBPACK_IMPORTED_MODULE_0___default.a.createElement(react_jhipster__WEBPACK_IMPORTED_MODULE_4__["Translate"], { contentKey: "entity.action.view" }, "View"))),
                        react__WEBPACK_IMPORTED_MODULE_0___default.a.createElement(reactstrap__WEBPACK_IMPORTED_MODULE_3__["Button"], { tag: react_router_dom__WEBPACK_IMPORTED_MODULE_2__["Link"], to: `${match.url}/${user.login}/edit`, color: "primary", size: "sm" },
                            react__WEBPACK_IMPORTED_MODULE_0___default.a.createElement(_fortawesome_react_fontawesome__WEBPACK_IMPORTED_MODULE_5__["FontAwesomeIcon"], { icon: "pencil-alt" }),
                            ' ',
                            react__WEBPACK_IMPORTED_MODULE_0___default.a.createElement("span", { className: "d-none d-md-inline" },
                                react__WEBPACK_IMPORTED_MODULE_0___default.a.createElement(react_jhipster__WEBPACK_IMPORTED_MODULE_4__["Translate"], { contentKey: "entity.action.edit" }, "Edit"))),
                        react__WEBPACK_IMPORTED_MODULE_0___default.a.createElement(reactstrap__WEBPACK_IMPORTED_MODULE_3__["Button"], { tag: react_router_dom__WEBPACK_IMPORTED_MODULE_2__["Link"], to: `${match.url}/${user.login}/delete`, color: "danger", size: "sm", disabled: account.login === user.login },
                            react__WEBPACK_IMPORTED_MODULE_0___default.a.createElement(_fortawesome_react_fontawesome__WEBPACK_IMPORTED_MODULE_5__["FontAwesomeIcon"], { icon: "trash" }),
                            ' ',
                            react__WEBPACK_IMPORTED_MODULE_0___default.a.createElement("span", { className: "d-none d-md-inline" },
                                react__WEBPACK_IMPORTED_MODULE_0___default.a.createElement(react_jhipster__WEBPACK_IMPORTED_MODULE_4__["Translate"], { contentKey: "entity.action.delete" }, "Delete")))))))))),
        props.totalItems ? (react__WEBPACK_IMPORTED_MODULE_0___default.a.createElement("div", { className: users && users.length > 0 ? '' : 'd-none' },
            react__WEBPACK_IMPORTED_MODULE_0___default.a.createElement(reactstrap__WEBPACK_IMPORTED_MODULE_3__["Row"], { className: "justify-content-center" },
                react__WEBPACK_IMPORTED_MODULE_0___default.a.createElement(react_jhipster__WEBPACK_IMPORTED_MODULE_4__["JhiItemCount"], { page: pagination.activePage, total: totalItems, itemsPerPage: pagination.itemsPerPage, i18nEnabled: true })),
            react__WEBPACK_IMPORTED_MODULE_0___default.a.createElement(reactstrap__WEBPACK_IMPORTED_MODULE_3__["Row"], { className: "justify-content-center" },
                react__WEBPACK_IMPORTED_MODULE_0___default.a.createElement(react_jhipster__WEBPACK_IMPORTED_MODULE_4__["JhiPagination"], { activePage: pagination.activePage, onSelect: handlePagination, maxButtons: 5, itemsPerPage: pagination.itemsPerPage, totalItems: props.totalItems })))) : ('')));
};
const mapStateToProps = (storeState) => ({
    users: storeState.userManagement.users,
    totalItems: storeState.userManagement.totalItems,
    account: storeState.authentication.account,
});
const mapDispatchToProps = { getUsers: _user_management_reducer__WEBPACK_IMPORTED_MODULE_9__["getUsers"], updateUser: _user_management_reducer__WEBPACK_IMPORTED_MODULE_9__["updateUser"] };
/* harmony default export */ __webpack_exports__["default"] = (Object(react_redux__WEBPACK_IMPORTED_MODULE_1__["connect"])(mapStateToProps, mapDispatchToProps)(UserManagement));

 void function register() { /* react-hot-loader/webpack */ var reactHotLoader = typeof reactHotLoaderGlobal !== 'undefined' ? reactHotLoaderGlobal.default : undefined; if (!reactHotLoader) { return; } /* eslint-disable camelcase, no-undef */ var webpackExports = typeof __webpack_exports__ !== 'undefined' ? __webpack_exports__ : exports; /* eslint-enable camelcase, no-undef */ if (!webpackExports) { return; } if (typeof webpackExports === 'function') { reactHotLoader.register(webpackExports, 'module.exports', "C:\\gateway\\src\\main\\webapp\\app\\modules\\administration\\user-management\\user-management.tsx"); return; } /* eslint-disable no-restricted-syntax */ for (var key in webpackExports) { /* eslint-enable no-restricted-syntax */ if (!Object.prototype.hasOwnProperty.call(webpackExports, key)) { continue; } var namedExport = void 0; try { namedExport = webpackExports[key]; } catch (err) { continue; } reactHotLoader.register(namedExport, key, "C:\\gateway\\src\\main\\webapp\\app\\modules\\administration\\user-management\\user-management.tsx"); } }(); 

/***/ })

}]);
//# sourceMappingURL=administration.chunk.js.map