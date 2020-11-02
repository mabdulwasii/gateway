import axios from 'axios';
import { ICrudGetAction, ICrudGetAllAction, ICrudPutAction, ICrudDeleteAction } from 'react-jhipster';

import { cleanEntity } from 'app/shared/util/entity-utils';
import { REQUEST, SUCCESS, FAILURE } from 'app/shared/reducers/action-type.util';

import { IWalletAccountTypeMySuffix, defaultValue } from 'app/shared/model/wallet-account-type-my-suffix.model';

export const ACTION_TYPES = {
  FETCH_WALLETACCOUNTTYPE_LIST: 'walletAccountType/FETCH_WALLETACCOUNTTYPE_LIST',
  FETCH_WALLETACCOUNTTYPE: 'walletAccountType/FETCH_WALLETACCOUNTTYPE',
  CREATE_WALLETACCOUNTTYPE: 'walletAccountType/CREATE_WALLETACCOUNTTYPE',
  UPDATE_WALLETACCOUNTTYPE: 'walletAccountType/UPDATE_WALLETACCOUNTTYPE',
  DELETE_WALLETACCOUNTTYPE: 'walletAccountType/DELETE_WALLETACCOUNTTYPE',
  RESET: 'walletAccountType/RESET',
};

const initialState = {
  loading: false,
  errorMessage: null,
  entities: [] as ReadonlyArray<IWalletAccountTypeMySuffix>,
  entity: defaultValue,
  updating: false,
  updateSuccess: false,
};

export type WalletAccountTypeMySuffixState = Readonly<typeof initialState>;

// Reducer

export default (state: WalletAccountTypeMySuffixState = initialState, action): WalletAccountTypeMySuffixState => {
  switch (action.type) {
    case REQUEST(ACTION_TYPES.FETCH_WALLETACCOUNTTYPE_LIST):
    case REQUEST(ACTION_TYPES.FETCH_WALLETACCOUNTTYPE):
      return {
        ...state,
        errorMessage: null,
        updateSuccess: false,
        loading: true,
      };
    case REQUEST(ACTION_TYPES.CREATE_WALLETACCOUNTTYPE):
    case REQUEST(ACTION_TYPES.UPDATE_WALLETACCOUNTTYPE):
    case REQUEST(ACTION_TYPES.DELETE_WALLETACCOUNTTYPE):
      return {
        ...state,
        errorMessage: null,
        updateSuccess: false,
        updating: true,
      };
    case FAILURE(ACTION_TYPES.FETCH_WALLETACCOUNTTYPE_LIST):
    case FAILURE(ACTION_TYPES.FETCH_WALLETACCOUNTTYPE):
    case FAILURE(ACTION_TYPES.CREATE_WALLETACCOUNTTYPE):
    case FAILURE(ACTION_TYPES.UPDATE_WALLETACCOUNTTYPE):
    case FAILURE(ACTION_TYPES.DELETE_WALLETACCOUNTTYPE):
      return {
        ...state,
        loading: false,
        updating: false,
        updateSuccess: false,
        errorMessage: action.payload,
      };
    case SUCCESS(ACTION_TYPES.FETCH_WALLETACCOUNTTYPE_LIST):
      return {
        ...state,
        loading: false,
        entities: action.payload.data,
      };
    case SUCCESS(ACTION_TYPES.FETCH_WALLETACCOUNTTYPE):
      return {
        ...state,
        loading: false,
        entity: action.payload.data,
      };
    case SUCCESS(ACTION_TYPES.CREATE_WALLETACCOUNTTYPE):
    case SUCCESS(ACTION_TYPES.UPDATE_WALLETACCOUNTTYPE):
      return {
        ...state,
        updating: false,
        updateSuccess: true,
        entity: action.payload.data,
      };
    case SUCCESS(ACTION_TYPES.DELETE_WALLETACCOUNTTYPE):
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

const apiUrl = 'api/wallet-account-types';

// Actions

export const getEntities: ICrudGetAllAction<IWalletAccountTypeMySuffix> = (page, size, sort) => ({
  type: ACTION_TYPES.FETCH_WALLETACCOUNTTYPE_LIST,
  payload: axios.get<IWalletAccountTypeMySuffix>(`${apiUrl}?cacheBuster=${new Date().getTime()}`),
});

export const getEntity: ICrudGetAction<IWalletAccountTypeMySuffix> = id => {
  const requestUrl = `${apiUrl}/${id}`;
  return {
    type: ACTION_TYPES.FETCH_WALLETACCOUNTTYPE,
    payload: axios.get<IWalletAccountTypeMySuffix>(requestUrl),
  };
};

export const createEntity: ICrudPutAction<IWalletAccountTypeMySuffix> = entity => async dispatch => {
  const result = await dispatch({
    type: ACTION_TYPES.CREATE_WALLETACCOUNTTYPE,
    payload: axios.post(apiUrl, cleanEntity(entity)),
  });
  dispatch(getEntities());
  return result;
};

export const updateEntity: ICrudPutAction<IWalletAccountTypeMySuffix> = entity => async dispatch => {
  const result = await dispatch({
    type: ACTION_TYPES.UPDATE_WALLETACCOUNTTYPE,
    payload: axios.put(apiUrl, cleanEntity(entity)),
  });
  return result;
};

export const deleteEntity: ICrudDeleteAction<IWalletAccountTypeMySuffix> = id => async dispatch => {
  const requestUrl = `${apiUrl}/${id}`;
  const result = await dispatch({
    type: ACTION_TYPES.DELETE_WALLETACCOUNTTYPE,
    payload: axios.delete(requestUrl),
  });
  dispatch(getEntities());
  return result;
};

export const reset = () => ({
  type: ACTION_TYPES.RESET,
});
