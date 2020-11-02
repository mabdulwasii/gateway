import axios from 'axios';
import { ICrudGetAction, ICrudGetAllAction, ICrudPutAction, ICrudDeleteAction } from 'react-jhipster';

import { cleanEntity } from 'app/shared/util/entity-utils';
import { REQUEST, SUCCESS, FAILURE } from 'app/shared/reducers/action-type.util';

import { IWalletAccountMySuffix, defaultValue } from 'app/shared/model/wallet-account-my-suffix.model';

export const ACTION_TYPES = {
  FETCH_WALLETACCOUNT_LIST: 'walletAccount/FETCH_WALLETACCOUNT_LIST',
  FETCH_WALLETACCOUNT: 'walletAccount/FETCH_WALLETACCOUNT',
  CREATE_WALLETACCOUNT: 'walletAccount/CREATE_WALLETACCOUNT',
  UPDATE_WALLETACCOUNT: 'walletAccount/UPDATE_WALLETACCOUNT',
  DELETE_WALLETACCOUNT: 'walletAccount/DELETE_WALLETACCOUNT',
  RESET: 'walletAccount/RESET',
};

const initialState = {
  loading: false,
  errorMessage: null,
  entities: [] as ReadonlyArray<IWalletAccountMySuffix>,
  entity: defaultValue,
  updating: false,
  updateSuccess: false,
};

export type WalletAccountMySuffixState = Readonly<typeof initialState>;

// Reducer

export default (state: WalletAccountMySuffixState = initialState, action): WalletAccountMySuffixState => {
  switch (action.type) {
    case REQUEST(ACTION_TYPES.FETCH_WALLETACCOUNT_LIST):
    case REQUEST(ACTION_TYPES.FETCH_WALLETACCOUNT):
      return {
        ...state,
        errorMessage: null,
        updateSuccess: false,
        loading: true,
      };
    case REQUEST(ACTION_TYPES.CREATE_WALLETACCOUNT):
    case REQUEST(ACTION_TYPES.UPDATE_WALLETACCOUNT):
    case REQUEST(ACTION_TYPES.DELETE_WALLETACCOUNT):
      return {
        ...state,
        errorMessage: null,
        updateSuccess: false,
        updating: true,
      };
    case FAILURE(ACTION_TYPES.FETCH_WALLETACCOUNT_LIST):
    case FAILURE(ACTION_TYPES.FETCH_WALLETACCOUNT):
    case FAILURE(ACTION_TYPES.CREATE_WALLETACCOUNT):
    case FAILURE(ACTION_TYPES.UPDATE_WALLETACCOUNT):
    case FAILURE(ACTION_TYPES.DELETE_WALLETACCOUNT):
      return {
        ...state,
        loading: false,
        updating: false,
        updateSuccess: false,
        errorMessage: action.payload,
      };
    case SUCCESS(ACTION_TYPES.FETCH_WALLETACCOUNT_LIST):
      return {
        ...state,
        loading: false,
        entities: action.payload.data,
      };
    case SUCCESS(ACTION_TYPES.FETCH_WALLETACCOUNT):
      return {
        ...state,
        loading: false,
        entity: action.payload.data,
      };
    case SUCCESS(ACTION_TYPES.CREATE_WALLETACCOUNT):
    case SUCCESS(ACTION_TYPES.UPDATE_WALLETACCOUNT):
      return {
        ...state,
        updating: false,
        updateSuccess: true,
        entity: action.payload.data,
      };
    case SUCCESS(ACTION_TYPES.DELETE_WALLETACCOUNT):
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

const apiUrl = 'api/wallet-accounts';

// Actions

export const getEntities: ICrudGetAllAction<IWalletAccountMySuffix> = (page, size, sort) => ({
  type: ACTION_TYPES.FETCH_WALLETACCOUNT_LIST,
  payload: axios.get<IWalletAccountMySuffix>(`${apiUrl}?cacheBuster=${new Date().getTime()}`),
});

export const getEntity: ICrudGetAction<IWalletAccountMySuffix> = id => {
  const requestUrl = `${apiUrl}/${id}`;
  return {
    type: ACTION_TYPES.FETCH_WALLETACCOUNT,
    payload: axios.get<IWalletAccountMySuffix>(requestUrl),
  };
};

export const createEntity: ICrudPutAction<IWalletAccountMySuffix> = entity => async dispatch => {
  const result = await dispatch({
    type: ACTION_TYPES.CREATE_WALLETACCOUNT,
    payload: axios.post(apiUrl, cleanEntity(entity)),
  });
  dispatch(getEntities());
  return result;
};

export const updateEntity: ICrudPutAction<IWalletAccountMySuffix> = entity => async dispatch => {
  const result = await dispatch({
    type: ACTION_TYPES.UPDATE_WALLETACCOUNT,
    payload: axios.put(apiUrl, cleanEntity(entity)),
  });
  return result;
};

export const deleteEntity: ICrudDeleteAction<IWalletAccountMySuffix> = id => async dispatch => {
  const requestUrl = `${apiUrl}/${id}`;
  const result = await dispatch({
    type: ACTION_TYPES.DELETE_WALLETACCOUNT,
    payload: axios.delete(requestUrl),
  });
  dispatch(getEntities());
  return result;
};

export const reset = () => ({
  type: ACTION_TYPES.RESET,
});
