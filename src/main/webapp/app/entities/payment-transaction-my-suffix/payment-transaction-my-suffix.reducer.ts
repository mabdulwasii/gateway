import axios from 'axios';
import { ICrudGetAction, ICrudGetAllAction, ICrudPutAction, ICrudDeleteAction } from 'react-jhipster';

import { cleanEntity } from 'app/shared/util/entity-utils';
import { REQUEST, SUCCESS, FAILURE } from 'app/shared/reducers/action-type.util';

import { IPaymentTransactionMySuffix, defaultValue } from 'app/shared/model/payment-transaction-my-suffix.model';

export const ACTION_TYPES = {
  FETCH_PAYMENTTRANSACTION_LIST: 'paymentTransaction/FETCH_PAYMENTTRANSACTION_LIST',
  FETCH_PAYMENTTRANSACTION: 'paymentTransaction/FETCH_PAYMENTTRANSACTION',
  CREATE_PAYMENTTRANSACTION: 'paymentTransaction/CREATE_PAYMENTTRANSACTION',
  UPDATE_PAYMENTTRANSACTION: 'paymentTransaction/UPDATE_PAYMENTTRANSACTION',
  DELETE_PAYMENTTRANSACTION: 'paymentTransaction/DELETE_PAYMENTTRANSACTION',
  RESET: 'paymentTransaction/RESET',
};

const initialState = {
  loading: false,
  errorMessage: null,
  entities: [] as ReadonlyArray<IPaymentTransactionMySuffix>,
  entity: defaultValue,
  updating: false,
  totalItems: 0,
  updateSuccess: false,
};

export type PaymentTransactionMySuffixState = Readonly<typeof initialState>;

// Reducer

export default (state: PaymentTransactionMySuffixState = initialState, action): PaymentTransactionMySuffixState => {
  switch (action.type) {
    case REQUEST(ACTION_TYPES.FETCH_PAYMENTTRANSACTION_LIST):
    case REQUEST(ACTION_TYPES.FETCH_PAYMENTTRANSACTION):
      return {
        ...state,
        errorMessage: null,
        updateSuccess: false,
        loading: true,
      };
    case REQUEST(ACTION_TYPES.CREATE_PAYMENTTRANSACTION):
    case REQUEST(ACTION_TYPES.UPDATE_PAYMENTTRANSACTION):
    case REQUEST(ACTION_TYPES.DELETE_PAYMENTTRANSACTION):
      return {
        ...state,
        errorMessage: null,
        updateSuccess: false,
        updating: true,
      };
    case FAILURE(ACTION_TYPES.FETCH_PAYMENTTRANSACTION_LIST):
    case FAILURE(ACTION_TYPES.FETCH_PAYMENTTRANSACTION):
    case FAILURE(ACTION_TYPES.CREATE_PAYMENTTRANSACTION):
    case FAILURE(ACTION_TYPES.UPDATE_PAYMENTTRANSACTION):
    case FAILURE(ACTION_TYPES.DELETE_PAYMENTTRANSACTION):
      return {
        ...state,
        loading: false,
        updating: false,
        updateSuccess: false,
        errorMessage: action.payload,
      };
    case SUCCESS(ACTION_TYPES.FETCH_PAYMENTTRANSACTION_LIST):
      return {
        ...state,
        loading: false,
        entities: action.payload.data,
        totalItems: parseInt(action.payload.headers['x-total-count'], 10),
      };
    case SUCCESS(ACTION_TYPES.FETCH_PAYMENTTRANSACTION):
      return {
        ...state,
        loading: false,
        entity: action.payload.data,
      };
    case SUCCESS(ACTION_TYPES.CREATE_PAYMENTTRANSACTION):
    case SUCCESS(ACTION_TYPES.UPDATE_PAYMENTTRANSACTION):
      return {
        ...state,
        updating: false,
        updateSuccess: true,
        entity: action.payload.data,
      };
    case SUCCESS(ACTION_TYPES.DELETE_PAYMENTTRANSACTION):
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

const apiUrl = 'api/payment-transactions';

// Actions

export const getEntities: ICrudGetAllAction<IPaymentTransactionMySuffix> = (page, size, sort) => {
  const requestUrl = `${apiUrl}${sort ? `?page=${page}&size=${size}&sort=${sort}` : ''}`;
  return {
    type: ACTION_TYPES.FETCH_PAYMENTTRANSACTION_LIST,
    payload: axios.get<IPaymentTransactionMySuffix>(`${requestUrl}${sort ? '&' : '?'}cacheBuster=${new Date().getTime()}`),
  };
};

export const getEntity: ICrudGetAction<IPaymentTransactionMySuffix> = id => {
  const requestUrl = `${apiUrl}/${id}`;
  return {
    type: ACTION_TYPES.FETCH_PAYMENTTRANSACTION,
    payload: axios.get<IPaymentTransactionMySuffix>(requestUrl),
  };
};

export const createEntity: ICrudPutAction<IPaymentTransactionMySuffix> = entity => async dispatch => {
  const result = await dispatch({
    type: ACTION_TYPES.CREATE_PAYMENTTRANSACTION,
    payload: axios.post(apiUrl, cleanEntity(entity)),
  });
  dispatch(getEntities());
  return result;
};

export const updateEntity: ICrudPutAction<IPaymentTransactionMySuffix> = entity => async dispatch => {
  const result = await dispatch({
    type: ACTION_TYPES.UPDATE_PAYMENTTRANSACTION,
    payload: axios.put(apiUrl, cleanEntity(entity)),
  });
  return result;
};

export const deleteEntity: ICrudDeleteAction<IPaymentTransactionMySuffix> = id => async dispatch => {
  const requestUrl = `${apiUrl}/${id}`;
  const result = await dispatch({
    type: ACTION_TYPES.DELETE_PAYMENTTRANSACTION,
    payload: axios.delete(requestUrl),
  });
  dispatch(getEntities());
  return result;
};

export const reset = () => ({
  type: ACTION_TYPES.RESET,
});
