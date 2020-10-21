import axios from 'axios';
import { ICrudGetAction, ICrudGetAllAction, ICrudPutAction, ICrudDeleteAction } from 'react-jhipster';

import { cleanEntity } from 'app/shared/util/entity-utils';
import { REQUEST, SUCCESS, FAILURE } from 'app/shared/reducers/action-type.util';

import { IKyclevelMySuffix, defaultValue } from 'app/shared/model/kyclevel-my-suffix.model';

export const ACTION_TYPES = {
  FETCH_KYCLEVEL_LIST: 'kyclevel/FETCH_KYCLEVEL_LIST',
  FETCH_KYCLEVEL: 'kyclevel/FETCH_KYCLEVEL',
  CREATE_KYCLEVEL: 'kyclevel/CREATE_KYCLEVEL',
  UPDATE_KYCLEVEL: 'kyclevel/UPDATE_KYCLEVEL',
  DELETE_KYCLEVEL: 'kyclevel/DELETE_KYCLEVEL',
  RESET: 'kyclevel/RESET',
};

const initialState = {
  loading: false,
  errorMessage: null,
  entities: [] as ReadonlyArray<IKyclevelMySuffix>,
  entity: defaultValue,
  updating: false,
  updateSuccess: false,
};

export type KyclevelMySuffixState = Readonly<typeof initialState>;

// Reducer

export default (state: KyclevelMySuffixState = initialState, action): KyclevelMySuffixState => {
  switch (action.type) {
    case REQUEST(ACTION_TYPES.FETCH_KYCLEVEL_LIST):
    case REQUEST(ACTION_TYPES.FETCH_KYCLEVEL):
      return {
        ...state,
        errorMessage: null,
        updateSuccess: false,
        loading: true,
      };
    case REQUEST(ACTION_TYPES.CREATE_KYCLEVEL):
    case REQUEST(ACTION_TYPES.UPDATE_KYCLEVEL):
    case REQUEST(ACTION_TYPES.DELETE_KYCLEVEL):
      return {
        ...state,
        errorMessage: null,
        updateSuccess: false,
        updating: true,
      };
    case FAILURE(ACTION_TYPES.FETCH_KYCLEVEL_LIST):
    case FAILURE(ACTION_TYPES.FETCH_KYCLEVEL):
    case FAILURE(ACTION_TYPES.CREATE_KYCLEVEL):
    case FAILURE(ACTION_TYPES.UPDATE_KYCLEVEL):
    case FAILURE(ACTION_TYPES.DELETE_KYCLEVEL):
      return {
        ...state,
        loading: false,
        updating: false,
        updateSuccess: false,
        errorMessage: action.payload,
      };
    case SUCCESS(ACTION_TYPES.FETCH_KYCLEVEL_LIST):
      return {
        ...state,
        loading: false,
        entities: action.payload.data,
      };
    case SUCCESS(ACTION_TYPES.FETCH_KYCLEVEL):
      return {
        ...state,
        loading: false,
        entity: action.payload.data,
      };
    case SUCCESS(ACTION_TYPES.CREATE_KYCLEVEL):
    case SUCCESS(ACTION_TYPES.UPDATE_KYCLEVEL):
      return {
        ...state,
        updating: false,
        updateSuccess: true,
        entity: action.payload.data,
      };
    case SUCCESS(ACTION_TYPES.DELETE_KYCLEVEL):
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

const apiUrl = 'api/kyclevels';

// Actions

export const getEntities: ICrudGetAllAction<IKyclevelMySuffix> = (page, size, sort) => ({
  type: ACTION_TYPES.FETCH_KYCLEVEL_LIST,
  payload: axios.get<IKyclevelMySuffix>(`${apiUrl}?cacheBuster=${new Date().getTime()}`),
});

export const getEntity: ICrudGetAction<IKyclevelMySuffix> = id => {
  const requestUrl = `${apiUrl}/${id}`;
  return {
    type: ACTION_TYPES.FETCH_KYCLEVEL,
    payload: axios.get<IKyclevelMySuffix>(requestUrl),
  };
};

export const createEntity: ICrudPutAction<IKyclevelMySuffix> = entity => async dispatch => {
  const result = await dispatch({
    type: ACTION_TYPES.CREATE_KYCLEVEL,
    payload: axios.post(apiUrl, cleanEntity(entity)),
  });
  dispatch(getEntities());
  return result;
};

export const updateEntity: ICrudPutAction<IKyclevelMySuffix> = entity => async dispatch => {
  const result = await dispatch({
    type: ACTION_TYPES.UPDATE_KYCLEVEL,
    payload: axios.put(apiUrl, cleanEntity(entity)),
  });
  return result;
};

export const deleteEntity: ICrudDeleteAction<IKyclevelMySuffix> = id => async dispatch => {
  const requestUrl = `${apiUrl}/${id}`;
  const result = await dispatch({
    type: ACTION_TYPES.DELETE_KYCLEVEL,
    payload: axios.delete(requestUrl),
  });
  dispatch(getEntities());
  return result;
};

export const reset = () => ({
  type: ACTION_TYPES.RESET,
});
