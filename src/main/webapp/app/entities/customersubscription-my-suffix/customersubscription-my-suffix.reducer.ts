import axios from 'axios';
import { ICrudGetAction, ICrudGetAllAction, ICrudPutAction, ICrudDeleteAction } from 'react-jhipster';

import { cleanEntity } from 'app/shared/util/entity-utils';
import { REQUEST, SUCCESS, FAILURE } from 'app/shared/reducers/action-type.util';

import { ICustomersubscriptionMySuffix, defaultValue } from 'app/shared/model/customersubscription-my-suffix.model';

export const ACTION_TYPES = {
  FETCH_CUSTOMERSUBSCRIPTION_LIST: 'customersubscription/FETCH_CUSTOMERSUBSCRIPTION_LIST',
  FETCH_CUSTOMERSUBSCRIPTION: 'customersubscription/FETCH_CUSTOMERSUBSCRIPTION',
  CREATE_CUSTOMERSUBSCRIPTION: 'customersubscription/CREATE_CUSTOMERSUBSCRIPTION',
  UPDATE_CUSTOMERSUBSCRIPTION: 'customersubscription/UPDATE_CUSTOMERSUBSCRIPTION',
  DELETE_CUSTOMERSUBSCRIPTION: 'customersubscription/DELETE_CUSTOMERSUBSCRIPTION',
  RESET: 'customersubscription/RESET',
};

const initialState = {
  loading: false,
  errorMessage: null,
  entities: [] as ReadonlyArray<ICustomersubscriptionMySuffix>,
  entity: defaultValue,
  updating: false,
  updateSuccess: false,
};

export type CustomersubscriptionMySuffixState = Readonly<typeof initialState>;

// Reducer

export default (state: CustomersubscriptionMySuffixState = initialState, action): CustomersubscriptionMySuffixState => {
  switch (action.type) {
    case REQUEST(ACTION_TYPES.FETCH_CUSTOMERSUBSCRIPTION_LIST):
    case REQUEST(ACTION_TYPES.FETCH_CUSTOMERSUBSCRIPTION):
      return {
        ...state,
        errorMessage: null,
        updateSuccess: false,
        loading: true,
      };
    case REQUEST(ACTION_TYPES.CREATE_CUSTOMERSUBSCRIPTION):
    case REQUEST(ACTION_TYPES.UPDATE_CUSTOMERSUBSCRIPTION):
    case REQUEST(ACTION_TYPES.DELETE_CUSTOMERSUBSCRIPTION):
      return {
        ...state,
        errorMessage: null,
        updateSuccess: false,
        updating: true,
      };
    case FAILURE(ACTION_TYPES.FETCH_CUSTOMERSUBSCRIPTION_LIST):
    case FAILURE(ACTION_TYPES.FETCH_CUSTOMERSUBSCRIPTION):
    case FAILURE(ACTION_TYPES.CREATE_CUSTOMERSUBSCRIPTION):
    case FAILURE(ACTION_TYPES.UPDATE_CUSTOMERSUBSCRIPTION):
    case FAILURE(ACTION_TYPES.DELETE_CUSTOMERSUBSCRIPTION):
      return {
        ...state,
        loading: false,
        updating: false,
        updateSuccess: false,
        errorMessage: action.payload,
      };
    case SUCCESS(ACTION_TYPES.FETCH_CUSTOMERSUBSCRIPTION_LIST):
      return {
        ...state,
        loading: false,
        entities: action.payload.data,
      };
    case SUCCESS(ACTION_TYPES.FETCH_CUSTOMERSUBSCRIPTION):
      return {
        ...state,
        loading: false,
        entity: action.payload.data,
      };
    case SUCCESS(ACTION_TYPES.CREATE_CUSTOMERSUBSCRIPTION):
    case SUCCESS(ACTION_TYPES.UPDATE_CUSTOMERSUBSCRIPTION):
      return {
        ...state,
        updating: false,
        updateSuccess: true,
        entity: action.payload.data,
      };
    case SUCCESS(ACTION_TYPES.DELETE_CUSTOMERSUBSCRIPTION):
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

const apiUrl = 'api/customersubscriptions';

// Actions

export const getEntities: ICrudGetAllAction<ICustomersubscriptionMySuffix> = (page, size, sort) => ({
  type: ACTION_TYPES.FETCH_CUSTOMERSUBSCRIPTION_LIST,
  payload: axios.get<ICustomersubscriptionMySuffix>(`${apiUrl}?cacheBuster=${new Date().getTime()}`),
});

export const getEntity: ICrudGetAction<ICustomersubscriptionMySuffix> = id => {
  const requestUrl = `${apiUrl}/${id}`;
  return {
    type: ACTION_TYPES.FETCH_CUSTOMERSUBSCRIPTION,
    payload: axios.get<ICustomersubscriptionMySuffix>(requestUrl),
  };
};

export const createEntity: ICrudPutAction<ICustomersubscriptionMySuffix> = entity => async dispatch => {
  const result = await dispatch({
    type: ACTION_TYPES.CREATE_CUSTOMERSUBSCRIPTION,
    payload: axios.post(apiUrl, cleanEntity(entity)),
  });
  dispatch(getEntities());
  return result;
};

export const updateEntity: ICrudPutAction<ICustomersubscriptionMySuffix> = entity => async dispatch => {
  const result = await dispatch({
    type: ACTION_TYPES.UPDATE_CUSTOMERSUBSCRIPTION,
    payload: axios.put(apiUrl, cleanEntity(entity)),
  });
  return result;
};

export const deleteEntity: ICrudDeleteAction<ICustomersubscriptionMySuffix> = id => async dispatch => {
  const requestUrl = `${apiUrl}/${id}`;
  const result = await dispatch({
    type: ACTION_TYPES.DELETE_CUSTOMERSUBSCRIPTION,
    payload: axios.delete(requestUrl),
  });
  dispatch(getEntities());
  return result;
};

export const reset = () => ({
  type: ACTION_TYPES.RESET,
});
