import axios from 'axios';
import { ICrudGetAction, ICrudGetAllAction, ICrudPutAction, ICrudDeleteAction } from 'react-jhipster';

import { cleanEntity } from 'app/shared/util/entity-utils';
import { REQUEST, SUCCESS, FAILURE } from 'app/shared/reducers/action-type.util';

import { IBillerPlatformMySuffix, defaultValue } from 'app/shared/model/biller-platform-my-suffix.model';

export const ACTION_TYPES = {
  FETCH_BILLERPLATFORM_LIST: 'billerPlatform/FETCH_BILLERPLATFORM_LIST',
  FETCH_BILLERPLATFORM: 'billerPlatform/FETCH_BILLERPLATFORM',
  CREATE_BILLERPLATFORM: 'billerPlatform/CREATE_BILLERPLATFORM',
  UPDATE_BILLERPLATFORM: 'billerPlatform/UPDATE_BILLERPLATFORM',
  DELETE_BILLERPLATFORM: 'billerPlatform/DELETE_BILLERPLATFORM',
  RESET: 'billerPlatform/RESET',
};

const initialState = {
  loading: false,
  errorMessage: null,
  entities: [] as ReadonlyArray<IBillerPlatformMySuffix>,
  entity: defaultValue,
  updating: false,
  updateSuccess: false,
};

export type BillerPlatformMySuffixState = Readonly<typeof initialState>;

// Reducer

export default (state: BillerPlatformMySuffixState = initialState, action): BillerPlatformMySuffixState => {
  switch (action.type) {
    case REQUEST(ACTION_TYPES.FETCH_BILLERPLATFORM_LIST):
    case REQUEST(ACTION_TYPES.FETCH_BILLERPLATFORM):
      return {
        ...state,
        errorMessage: null,
        updateSuccess: false,
        loading: true,
      };
    case REQUEST(ACTION_TYPES.CREATE_BILLERPLATFORM):
    case REQUEST(ACTION_TYPES.UPDATE_BILLERPLATFORM):
    case REQUEST(ACTION_TYPES.DELETE_BILLERPLATFORM):
      return {
        ...state,
        errorMessage: null,
        updateSuccess: false,
        updating: true,
      };
    case FAILURE(ACTION_TYPES.FETCH_BILLERPLATFORM_LIST):
    case FAILURE(ACTION_TYPES.FETCH_BILLERPLATFORM):
    case FAILURE(ACTION_TYPES.CREATE_BILLERPLATFORM):
    case FAILURE(ACTION_TYPES.UPDATE_BILLERPLATFORM):
    case FAILURE(ACTION_TYPES.DELETE_BILLERPLATFORM):
      return {
        ...state,
        loading: false,
        updating: false,
        updateSuccess: false,
        errorMessage: action.payload,
      };
    case SUCCESS(ACTION_TYPES.FETCH_BILLERPLATFORM_LIST):
      return {
        ...state,
        loading: false,
        entities: action.payload.data,
      };
    case SUCCESS(ACTION_TYPES.FETCH_BILLERPLATFORM):
      return {
        ...state,
        loading: false,
        entity: action.payload.data,
      };
    case SUCCESS(ACTION_TYPES.CREATE_BILLERPLATFORM):
    case SUCCESS(ACTION_TYPES.UPDATE_BILLERPLATFORM):
      return {
        ...state,
        updating: false,
        updateSuccess: true,
        entity: action.payload.data,
      };
    case SUCCESS(ACTION_TYPES.DELETE_BILLERPLATFORM):
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

const apiUrl = 'api/biller-platforms';

// Actions

export const getEntities: ICrudGetAllAction<IBillerPlatformMySuffix> = (page, size, sort) => ({
  type: ACTION_TYPES.FETCH_BILLERPLATFORM_LIST,
  payload: axios.get<IBillerPlatformMySuffix>(`${apiUrl}?cacheBuster=${new Date().getTime()}`),
});

export const getEntity: ICrudGetAction<IBillerPlatformMySuffix> = id => {
  const requestUrl = `${apiUrl}/${id}`;
  return {
    type: ACTION_TYPES.FETCH_BILLERPLATFORM,
    payload: axios.get<IBillerPlatformMySuffix>(requestUrl),
  };
};

export const createEntity: ICrudPutAction<IBillerPlatformMySuffix> = entity => async dispatch => {
  const result = await dispatch({
    type: ACTION_TYPES.CREATE_BILLERPLATFORM,
    payload: axios.post(apiUrl, cleanEntity(entity)),
  });
  dispatch(getEntities());
  return result;
};

export const updateEntity: ICrudPutAction<IBillerPlatformMySuffix> = entity => async dispatch => {
  const result = await dispatch({
    type: ACTION_TYPES.UPDATE_BILLERPLATFORM,
    payload: axios.put(apiUrl, cleanEntity(entity)),
  });
  return result;
};

export const deleteEntity: ICrudDeleteAction<IBillerPlatformMySuffix> = id => async dispatch => {
  const requestUrl = `${apiUrl}/${id}`;
  const result = await dispatch({
    type: ACTION_TYPES.DELETE_BILLERPLATFORM,
    payload: axios.delete(requestUrl),
  });
  dispatch(getEntities());
  return result;
};

export const reset = () => ({
  type: ACTION_TYPES.RESET,
});
