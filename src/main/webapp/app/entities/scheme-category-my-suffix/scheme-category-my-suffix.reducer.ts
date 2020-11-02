import axios from 'axios';
import { ICrudGetAction, ICrudGetAllAction, ICrudPutAction, ICrudDeleteAction } from 'react-jhipster';

import { cleanEntity } from 'app/shared/util/entity-utils';
import { REQUEST, SUCCESS, FAILURE } from 'app/shared/reducers/action-type.util';

import { ISchemeCategoryMySuffix, defaultValue } from 'app/shared/model/scheme-category-my-suffix.model';

export const ACTION_TYPES = {
  FETCH_SCHEMECATEGORY_LIST: 'schemeCategory/FETCH_SCHEMECATEGORY_LIST',
  FETCH_SCHEMECATEGORY: 'schemeCategory/FETCH_SCHEMECATEGORY',
  CREATE_SCHEMECATEGORY: 'schemeCategory/CREATE_SCHEMECATEGORY',
  UPDATE_SCHEMECATEGORY: 'schemeCategory/UPDATE_SCHEMECATEGORY',
  DELETE_SCHEMECATEGORY: 'schemeCategory/DELETE_SCHEMECATEGORY',
  RESET: 'schemeCategory/RESET',
};

const initialState = {
  loading: false,
  errorMessage: null,
  entities: [] as ReadonlyArray<ISchemeCategoryMySuffix>,
  entity: defaultValue,
  updating: false,
  updateSuccess: false,
};

export type SchemeCategoryMySuffixState = Readonly<typeof initialState>;

// Reducer

export default (state: SchemeCategoryMySuffixState = initialState, action): SchemeCategoryMySuffixState => {
  switch (action.type) {
    case REQUEST(ACTION_TYPES.FETCH_SCHEMECATEGORY_LIST):
    case REQUEST(ACTION_TYPES.FETCH_SCHEMECATEGORY):
      return {
        ...state,
        errorMessage: null,
        updateSuccess: false,
        loading: true,
      };
    case REQUEST(ACTION_TYPES.CREATE_SCHEMECATEGORY):
    case REQUEST(ACTION_TYPES.UPDATE_SCHEMECATEGORY):
    case REQUEST(ACTION_TYPES.DELETE_SCHEMECATEGORY):
      return {
        ...state,
        errorMessage: null,
        updateSuccess: false,
        updating: true,
      };
    case FAILURE(ACTION_TYPES.FETCH_SCHEMECATEGORY_LIST):
    case FAILURE(ACTION_TYPES.FETCH_SCHEMECATEGORY):
    case FAILURE(ACTION_TYPES.CREATE_SCHEMECATEGORY):
    case FAILURE(ACTION_TYPES.UPDATE_SCHEMECATEGORY):
    case FAILURE(ACTION_TYPES.DELETE_SCHEMECATEGORY):
      return {
        ...state,
        loading: false,
        updating: false,
        updateSuccess: false,
        errorMessage: action.payload,
      };
    case SUCCESS(ACTION_TYPES.FETCH_SCHEMECATEGORY_LIST):
      return {
        ...state,
        loading: false,
        entities: action.payload.data,
      };
    case SUCCESS(ACTION_TYPES.FETCH_SCHEMECATEGORY):
      return {
        ...state,
        loading: false,
        entity: action.payload.data,
      };
    case SUCCESS(ACTION_TYPES.CREATE_SCHEMECATEGORY):
    case SUCCESS(ACTION_TYPES.UPDATE_SCHEMECATEGORY):
      return {
        ...state,
        updating: false,
        updateSuccess: true,
        entity: action.payload.data,
      };
    case SUCCESS(ACTION_TYPES.DELETE_SCHEMECATEGORY):
      return {
        ...state,
        updating: false,
        updateSuccess: true,
        entity: {},
      };
    case ACTION_TYPES.RESET:
      return {
        ...initialState,
      };
    default:
      return state;
  }
};

const apiUrl = 'api/scheme-categories';

// Actions

export const getEntities: ICrudGetAllAction<ISchemeCategoryMySuffix> = (page, size, sort) => ({
  type: ACTION_TYPES.FETCH_SCHEMECATEGORY_LIST,
  payload: axios.get<ISchemeCategoryMySuffix>(`${apiUrl}?cacheBuster=${new Date().getTime()}`),
});

export const getEntity: ICrudGetAction<ISchemeCategoryMySuffix> = id => {
  const requestUrl = `${apiUrl}/${id}`;
  return {
    type: ACTION_TYPES.FETCH_SCHEMECATEGORY,
    payload: axios.get<ISchemeCategoryMySuffix>(requestUrl),
  };
};

export const createEntity: ICrudPutAction<ISchemeCategoryMySuffix> = entity => async dispatch => {
  const result = await dispatch({
    type: ACTION_TYPES.CREATE_SCHEMECATEGORY,
    payload: axios.post(apiUrl, cleanEntity(entity)),
  });
  dispatch(getEntities());
  return result;
};

export const updateEntity: ICrudPutAction<ISchemeCategoryMySuffix> = entity => async dispatch => {
  const result = await dispatch({
    type: ACTION_TYPES.UPDATE_SCHEMECATEGORY,
    payload: axios.put(apiUrl, cleanEntity(entity)),
  });
  return result;
};

export const deleteEntity: ICrudDeleteAction<ISchemeCategoryMySuffix> = id => async dispatch => {
  const requestUrl = `${apiUrl}/${id}`;
  const result = await dispatch({
    type: ACTION_TYPES.DELETE_SCHEMECATEGORY,
    payload: axios.delete(requestUrl),
  });
  dispatch(getEntities());
  return result;
};

export const reset = () => ({
  type: ACTION_TYPES.RESET,
});
