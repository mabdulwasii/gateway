import axios from 'axios';
import { ICrudGetAction, ICrudGetAllAction, ICrudPutAction, ICrudDeleteAction } from 'react-jhipster';

import { cleanEntity } from 'app/shared/util/entity-utils';
import { REQUEST, SUCCESS, FAILURE } from 'app/shared/reducers/action-type.util';

import { ICountrolAccountMySuffix, defaultValue } from 'app/shared/model/countrol-account-my-suffix.model';

export const ACTION_TYPES = {
  FETCH_COUNTROLACCOUNT_LIST: 'countrolAccount/FETCH_COUNTROLACCOUNT_LIST',
  FETCH_COUNTROLACCOUNT: 'countrolAccount/FETCH_COUNTROLACCOUNT',
  CREATE_COUNTROLACCOUNT: 'countrolAccount/CREATE_COUNTROLACCOUNT',
  UPDATE_COUNTROLACCOUNT: 'countrolAccount/UPDATE_COUNTROLACCOUNT',
  DELETE_COUNTROLACCOUNT: 'countrolAccount/DELETE_COUNTROLACCOUNT',
  RESET: 'countrolAccount/RESET',
};

const initialState = {
  loading: false,
  errorMessage: null,
  entities: [] as ReadonlyArray<ICountrolAccountMySuffix>,
  entity: defaultValue,
  updating: false,
  updateSuccess: false,
};

export type CountrolAccountMySuffixState = Readonly<typeof initialState>;

// Reducer

export default (state: CountrolAccountMySuffixState = initialState, action): CountrolAccountMySuffixState => {
  switch (action.type) {
    case REQUEST(ACTION_TYPES.FETCH_COUNTROLACCOUNT_LIST):
    case REQUEST(ACTION_TYPES.FETCH_COUNTROLACCOUNT):
      return {
        ...state,
        errorMessage: null,
        updateSuccess: false,
        loading: true,
      };
    case REQUEST(ACTION_TYPES.CREATE_COUNTROLACCOUNT):
    case REQUEST(ACTION_TYPES.UPDATE_COUNTROLACCOUNT):
    case REQUEST(ACTION_TYPES.DELETE_COUNTROLACCOUNT):
      return {
        ...state,
        errorMessage: null,
        updateSuccess: false,
        updating: true,
      };
    case FAILURE(ACTION_TYPES.FETCH_COUNTROLACCOUNT_LIST):
    case FAILURE(ACTION_TYPES.FETCH_COUNTROLACCOUNT):
    case FAILURE(ACTION_TYPES.CREATE_COUNTROLACCOUNT):
    case FAILURE(ACTION_TYPES.UPDATE_COUNTROLACCOUNT):
    case FAILURE(ACTION_TYPES.DELETE_COUNTROLACCOUNT):
      return {
        ...state,
        loading: false,
        updating: false,
        updateSuccess: false,
        errorMessage: action.payload,
      };
    case SUCCESS(ACTION_TYPES.FETCH_COUNTROLACCOUNT_LIST):
      return {
        ...state,
        loading: false,
        entities: action.payload.data,
      };
    case SUCCESS(ACTION_TYPES.FETCH_COUNTROLACCOUNT):
      return {
        ...state,
        loading: false,
        entity: action.payload.data,
      };
    case SUCCESS(ACTION_TYPES.CREATE_COUNTROLACCOUNT):
    case SUCCESS(ACTION_TYPES.UPDATE_COUNTROLACCOUNT):
      return {
        ...state,
        updating: false,
        updateSuccess: true,
        entity: action.payload.data,
      };
    case SUCCESS(ACTION_TYPES.DELETE_COUNTROLACCOUNT):
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

const apiUrl = 'api/countrol-accounts';

// Actions

export const getEntities: ICrudGetAllAction<ICountrolAccountMySuffix> = (page, size, sort) => ({
  type: ACTION_TYPES.FETCH_COUNTROLACCOUNT_LIST,
  payload: axios.get<ICountrolAccountMySuffix>(`${apiUrl}?cacheBuster=${new Date().getTime()}`),
});

export const getEntity: ICrudGetAction<ICountrolAccountMySuffix> = id => {
  const requestUrl = `${apiUrl}/${id}`;
  return {
    type: ACTION_TYPES.FETCH_COUNTROLACCOUNT,
    payload: axios.get<ICountrolAccountMySuffix>(requestUrl),
  };
};

export const createEntity: ICrudPutAction<ICountrolAccountMySuffix> = entity => async dispatch => {
  const result = await dispatch({
    type: ACTION_TYPES.CREATE_COUNTROLACCOUNT,
    payload: axios.post(apiUrl, cleanEntity(entity)),
  });
  dispatch(getEntities());
  return result;
};

export const updateEntity: ICrudPutAction<ICountrolAccountMySuffix> = entity => async dispatch => {
  const result = await dispatch({
    type: ACTION_TYPES.UPDATE_COUNTROLACCOUNT,
    payload: axios.put(apiUrl, cleanEntity(entity)),
  });
  return result;
};

export const deleteEntity: ICrudDeleteAction<ICountrolAccountMySuffix> = id => async dispatch => {
  const requestUrl = `${apiUrl}/${id}`;
  const result = await dispatch({
    type: ACTION_TYPES.DELETE_COUNTROLACCOUNT,
    payload: axios.delete(requestUrl),
  });
  dispatch(getEntities());
  return result;
};

export const reset = () => ({
  type: ACTION_TYPES.RESET,
});
