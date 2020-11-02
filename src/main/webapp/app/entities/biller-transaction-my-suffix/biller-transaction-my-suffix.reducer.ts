import axios from 'axios';
import { ICrudGetAction, ICrudGetAllAction, ICrudPutAction, ICrudDeleteAction } from 'react-jhipster';

import { cleanEntity } from 'app/shared/util/entity-utils';
import { REQUEST, SUCCESS, FAILURE } from 'app/shared/reducers/action-type.util';

import { IBillerTransactionMySuffix, defaultValue } from 'app/shared/model/biller-transaction-my-suffix.model';

export const ACTION_TYPES = {
  FETCH_BILLERTRANSACTION_LIST: 'billerTransaction/FETCH_BILLERTRANSACTION_LIST',
  FETCH_BILLERTRANSACTION: 'billerTransaction/FETCH_BILLERTRANSACTION',
  CREATE_BILLERTRANSACTION: 'billerTransaction/CREATE_BILLERTRANSACTION',
  UPDATE_BILLERTRANSACTION: 'billerTransaction/UPDATE_BILLERTRANSACTION',
  DELETE_BILLERTRANSACTION: 'billerTransaction/DELETE_BILLERTRANSACTION',
  RESET: 'billerTransaction/RESET',
};

const initialState = {
  loading: false,
  errorMessage: null,
  entities: [] as ReadonlyArray<IBillerTransactionMySuffix>,
  entity: defaultValue,
  updating: false,
  totalItems: 0,
  updateSuccess: false,
};

export type BillerTransactionMySuffixState = Readonly<typeof initialState>;

// Reducer

export default (state: BillerTransactionMySuffixState = initialState, action): BillerTransactionMySuffixState => {
  switch (action.type) {
    case REQUEST(ACTION_TYPES.FETCH_BILLERTRANSACTION_LIST):
    case REQUEST(ACTION_TYPES.FETCH_BILLERTRANSACTION):
      return {
        ...state,
        errorMessage: null,
        updateSuccess: false,
        loading: true,
      };
    case REQUEST(ACTION_TYPES.CREATE_BILLERTRANSACTION):
    case REQUEST(ACTION_TYPES.UPDATE_BILLERTRANSACTION):
    case REQUEST(ACTION_TYPES.DELETE_BILLERTRANSACTION):
      return {
        ...state,
        errorMessage: null,
        updateSuccess: false,
        updating: true,
      };
    case FAILURE(ACTION_TYPES.FETCH_BILLERTRANSACTION_LIST):
    case FAILURE(ACTION_TYPES.FETCH_BILLERTRANSACTION):
    case FAILURE(ACTION_TYPES.CREATE_BILLERTRANSACTION):
    case FAILURE(ACTION_TYPES.UPDATE_BILLERTRANSACTION):
    case FAILURE(ACTION_TYPES.DELETE_BILLERTRANSACTION):
      return {
        ...state,
        loading: false,
        updating: false,
        updateSuccess: false,
        errorMessage: action.payload,
      };
    case SUCCESS(ACTION_TYPES.FETCH_BILLERTRANSACTION_LIST):
      return {
        ...state,
        loading: false,
        entities: action.payload.data,
        totalItems: parseInt(action.payload.headers['x-total-count'], 10),
      };
    case SUCCESS(ACTION_TYPES.FETCH_BILLERTRANSACTION):
      return {
        ...state,
        loading: false,
        entity: action.payload.data,
      };
    case SUCCESS(ACTION_TYPES.CREATE_BILLERTRANSACTION):
    case SUCCESS(ACTION_TYPES.UPDATE_BILLERTRANSACTION):
      return {
        ...state,
        updating: false,
        updateSuccess: true,
        entity: action.payload.data,
      };
    case SUCCESS(ACTION_TYPES.DELETE_BILLERTRANSACTION):
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

const apiUrl = 'api/biller-transactions';

// Actions

export const getEntities: ICrudGetAllAction<IBillerTransactionMySuffix> = (page, size, sort) => {
  const requestUrl = `${apiUrl}${sort ? `?page=${page}&size=${size}&sort=${sort}` : ''}`;
  return {
    type: ACTION_TYPES.FETCH_BILLERTRANSACTION_LIST,
    payload: axios.get<IBillerTransactionMySuffix>(`${requestUrl}${sort ? '&' : '?'}cacheBuster=${new Date().getTime()}`),
  };
};

export const getEntity: ICrudGetAction<IBillerTransactionMySuffix> = id => {
  const requestUrl = `${apiUrl}/${id}`;
  return {
    type: ACTION_TYPES.FETCH_BILLERTRANSACTION,
    payload: axios.get<IBillerTransactionMySuffix>(requestUrl),
  };
};

export const createEntity: ICrudPutAction<IBillerTransactionMySuffix> = entity => async dispatch => {
  const result = await dispatch({
    type: ACTION_TYPES.CREATE_BILLERTRANSACTION,
    payload: axios.post(apiUrl, cleanEntity(entity)),
  });
  dispatch(getEntities());
  return result;
};

export const updateEntity: ICrudPutAction<IBillerTransactionMySuffix> = entity => async dispatch => {
  const result = await dispatch({
    type: ACTION_TYPES.UPDATE_BILLERTRANSACTION,
    payload: axios.put(apiUrl, cleanEntity(entity)),
  });
  return result;
};

export const deleteEntity: ICrudDeleteAction<IBillerTransactionMySuffix> = id => async dispatch => {
  const requestUrl = `${apiUrl}/${id}`;
  const result = await dispatch({
    type: ACTION_TYPES.DELETE_BILLERTRANSACTION,
    payload: axios.delete(requestUrl),
  });
  dispatch(getEntities());
  return result;
};

export const reset = () => ({
  type: ACTION_TYPES.RESET,
});
