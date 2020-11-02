import axios from 'axios';
import { ICrudGetAction, ICrudGetAllAction, ICrudPutAction, ICrudDeleteAction } from 'react-jhipster';

import { cleanEntity } from 'app/shared/util/entity-utils';
import { REQUEST, SUCCESS, FAILURE } from 'app/shared/reducers/action-type.util';

import { ISchemeMySuffix, defaultValue } from 'app/shared/model/scheme-my-suffix.model';

export const ACTION_TYPES = {
  FETCH_SCHEME_LIST: 'scheme/FETCH_SCHEME_LIST',
  FETCH_SCHEME: 'scheme/FETCH_SCHEME',
  CREATE_SCHEME: 'scheme/CREATE_SCHEME',
  UPDATE_SCHEME: 'scheme/UPDATE_SCHEME',
  DELETE_SCHEME: 'scheme/DELETE_SCHEME',
  RESET: 'scheme/RESET',
};

const initialState = {
  loading: false,
  errorMessage: null,
  entities: [] as ReadonlyArray<ISchemeMySuffix>,
  entity: defaultValue,
  updating: false,
  updateSuccess: false,
};

export type SchemeMySuffixState = Readonly<typeof initialState>;

// Reducer

export default (state: SchemeMySuffixState = initialState, action): SchemeMySuffixState => {
  switch (action.type) {
    case REQUEST(ACTION_TYPES.FETCH_SCHEME_LIST):
    case REQUEST(ACTION_TYPES.FETCH_SCHEME):
      return {
        ...state,
        errorMessage: null,
        updateSuccess: false,
        loading: true,
      };
    case REQUEST(ACTION_TYPES.CREATE_SCHEME):
    case REQUEST(ACTION_TYPES.UPDATE_SCHEME):
    case REQUEST(ACTION_TYPES.DELETE_SCHEME):
      return {
        ...state,
        errorMessage: null,
        updateSuccess: false,
        updating: true,
      };
    case FAILURE(ACTION_TYPES.FETCH_SCHEME_LIST):
    case FAILURE(ACTION_TYPES.FETCH_SCHEME):
    case FAILURE(ACTION_TYPES.CREATE_SCHEME):
    case FAILURE(ACTION_TYPES.UPDATE_SCHEME):
    case FAILURE(ACTION_TYPES.DELETE_SCHEME):
      return {
        ...state,
        loading: false,
        updating: false,
        updateSuccess: false,
        errorMessage: action.payload,
      };
    case SUCCESS(ACTION_TYPES.FETCH_SCHEME_LIST):
      return {
        ...state,
        loading: false,
        entities: action.payload.data,
      };
    case SUCCESS(ACTION_TYPES.FETCH_SCHEME):
      return {
        ...state,
        loading: false,
        entity: action.payload.data,
      };
    case SUCCESS(ACTION_TYPES.CREATE_SCHEME):
    case SUCCESS(ACTION_TYPES.UPDATE_SCHEME):
      return {
        ...state,
        updating: false,
        updateSuccess: true,
        entity: action.payload.data,
      };
    case SUCCESS(ACTION_TYPES.DELETE_SCHEME):
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

const apiUrl = 'api/schemes';

// Actions

export const getEntities: ICrudGetAllAction<ISchemeMySuffix> = (page, size, sort) => ({
  type: ACTION_TYPES.FETCH_SCHEME_LIST,
  payload: axios.get<ISchemeMySuffix>(`${apiUrl}?cacheBuster=${new Date().getTime()}`),
});

export const getEntity: ICrudGetAction<ISchemeMySuffix> = id => {
  const requestUrl = `${apiUrl}/${id}`;
  return {
    type: ACTION_TYPES.FETCH_SCHEME,
    payload: axios.get<ISchemeMySuffix>(requestUrl),
  };
};

export const createEntity: ICrudPutAction<ISchemeMySuffix> = entity => async dispatch => {
  const result = await dispatch({
    type: ACTION_TYPES.CREATE_SCHEME,
    payload: axios.post(apiUrl, cleanEntity(entity)),
  });
  dispatch(getEntities());
  return result;
};

export const updateEntity: ICrudPutAction<ISchemeMySuffix> = entity => async dispatch => {
  const result = await dispatch({
    type: ACTION_TYPES.UPDATE_SCHEME,
    payload: axios.put(apiUrl, cleanEntity(entity)),
  });
  return result;
};

export const deleteEntity: ICrudDeleteAction<ISchemeMySuffix> = id => async dispatch => {
  const requestUrl = `${apiUrl}/${id}`;
  const result = await dispatch({
    type: ACTION_TYPES.DELETE_SCHEME,
    payload: axios.delete(requestUrl),
  });
  dispatch(getEntities());
  return result;
};

export const reset = () => ({
  type: ACTION_TYPES.RESET,
});
